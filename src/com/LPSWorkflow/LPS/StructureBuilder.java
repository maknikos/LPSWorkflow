package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Builds structure of connected entities from 'connections' context maps
 */
public class StructureBuilder {
    private Map<String, Entity> reactiveRulesRootMap;
    private Map<String, Entity> goalsRootMap; //TODO make use of this from visualiser?
    private List<String> fluents;

    public StructureBuilder() {
        this.reactiveRulesRootMap = new HashMap<>();
        this.goalsRootMap = new HashMap<>();
        this.fluents = new ArrayList<>();
    }

    public void build(Map<Object, Object> reactiveRuleRoots,
                      Map<Object, Object> reactiveRuleConnections,
                      Map<Object, Object> goalRoots,
                      Map<Object, Object> goalConnections,
                      List<String> fluents)
    {
        this.fluents = fluents;

        buildChains(reactiveRulesRootMap, reactiveRuleRoots, reactiveRuleConnections, true);
        buildChains(goalsRootMap, goalRoots, goalConnections, false);

        // distinguish fluents from actions by replacing corresponding Action with Fluent object
        replaceFluents(reactiveRulesRootMap);
        replaceFluents(goalsRootMap);

        // merge common starts of multiChildEntities (e.g. OR)
        mergeCommonPaths(reactiveRulesRootMap);
        mergeCommonPaths(goalsRootMap);

        // merge fluents and their negations
        mergeFluents(reactiveRulesRootMap);
        mergeFluents(goalsRootMap);

        // remove multiChildEntities with only one child (connect directly)
        removeRedundantMultiChildEntity(reactiveRulesRootMap);
        removeRedundantMultiChildEntity(goalsRootMap);

        //flip the sign of negated fluents and use FalseNext
        flipNegatedFluents(reactiveRulesRootMap);
        flipNegatedFluents(goalsRootMap);

        addGoalDefinitions(reactiveRulesRootMap);
        addGoalDefinitions(goalsRootMap);
    }

    private void buildChains(Map<String, Entity> rootMap,
                             Map<Object, Object> ruleRoots, Map<Object, Object> connections,
                             boolean groupByAnd) {
        // Build connections of entities
        ruleRoots.forEach((root, next) -> {
            Entity currEntity = (Entity) root;
            Entity nextEntity = (Entity) next;
            do{
                currEntity.setNext(nextEntity);
                currEntity = nextEntity;
                nextEntity = (Entity) connections.get(currEntity);
            } while(nextEntity != null);

            // no next entity in the connections
            currEntity.setNext(new Exit());
        });

        // Merge common roots
        Map<String, List<Entity>> commonNameGroups = ruleRoots.keySet().stream()
                .map(root -> (Entity)root)
                .collect(Collectors.groupingBy(Entity::getName));

        commonNameGroups.forEach((name, entities) -> {
            // entities always contain at least one
            Entity entity = entities.get(0);
            // for only one child, just add straight to the rootMap
            if(entities.size() == 1){
                rootMap.put(name, entity);
            } else {
                // for multiple entities, make a multiChildEntity and add to rootMap
                List<Entity> nextEntities = entities.stream().filter(Entity::hasNext)
                        .map(Entity::getNext).collect(Collectors.toList());
                if (groupByAnd) {
                    And and = new And(nextEntities);
                    entity.setNext(and);
                } else {
                    Or or = new Or(nextEntities);
                    entity.setNext(or);
                }
                rootMap.put(name, entity);
            }
        });
    }

    private void replaceFluents(Map<String, Entity> rootMap) {
        List<String> affectedKeys = rootMap.entrySet().stream()
                .filter(entry -> isFluent(entry.getValue().getName()))
                .map(Map.Entry<String, Entity>::getKey).collect(Collectors.toList());
        rootMap.forEach((s, e) -> replaceFluentsNext(e));

        affectedKeys.forEach(k -> {
            rootMap.put(k, getReplacementFluent(rootMap.get(k)));
        });
    }

    private void replaceFluentsNext(Entity e) {
        Entity currEntity = e;
        Entity nextEntity;
        while(currEntity != null){
            // in case the current entity has multiple children
            if(!currEntity.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) currEntity).getNextEntities();

                List<Entity> nextFluentEntities = nextEntities.stream()
                        .filter(next -> isFluent(next.getName())).collect(Collectors.toList());

                // change the fluent roots of next paths
                nextFluentEntities.forEach(fluent -> {
                    nextEntities.remove(fluent);
                    nextEntities.add(getReplacementFluent(fluent));
                });
                nextEntities.forEach(this::replaceFluentsNext);
            }

            // continue along the path
            nextEntity = currEntity.getNext();
            // replace next entity if applicable
            if(nextEntity != null && isFluent(nextEntity.getName())){
                Fluent fluent = getReplacementFluent(nextEntity);
                currEntity.setNext(fluent);
                currEntity = fluent;
            } else {
                currEntity = nextEntity;
            }
        }
    }

    private void mergeCommonPaths(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::mergeCommonPathsNext);
    }

    private void mergeCommonPathsNext(Entity e) {
        // jump to multiChildEntity to perform merge
        Entity current = e;
        while(current != null){
            //loop through the path (by getNext) and branch out if there is multiChildEntity
            if(!current.hasSingleChild()){
                // merge possible common paths and branch out

                EntityType currentMultiType = current.getType();
                List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();

                // sort the next paths into groups according to shared entities
                Map<String, List<Entity>> commonStartGroups = nextEntities.stream()
                        .collect(Collectors.groupingBy(Entity::getName));

                // if a group has more than one entity, then merge & group their children
                commonStartGroups.values().stream().filter(group -> group.size() > 1).forEach(group -> {
                    // create a separate entity
                    Entity entity = group.get(0);
                    Entity mergedEntity;
                    if(!entity.hasSingleChild()){
                        // merge only if the entities' children are identical

                        //TODO when common entities are multiChildEntities, do we merge them?
                        //TODO this may mean comparing members of multiple multiChildEntities.. complicates things.

                    } else {
                        mergedEntity = createEntityFor(entity.getType(), entity.getName());
                        group.forEach(nextEntities::remove);
                        List<Entity> newNextEntities = group.stream().filter(Entity::hasNext)
                                .map(Entity::getNext).collect(Collectors.toList());
                        Entity nextMultiChildEntity = createMultiChildEntityFor(currentMultiType, newNextEntities);
                        mergedEntity.setNext(nextMultiChildEntity);
                        nextEntities.add(mergedEntity);

                        // repeat until the end reached, or no more common paths.
                        // note: we don't have to go through all nextEntities, since we are merging 'common beginnings'
                        mergeCommonPathsNext(mergedEntity);
                    }
                });
            }

            // continue through the path (there is no FalseNext used for Fluents yet, so just use getNext())
            current = current.getNext();
        }
    }

    private void mergeFluents(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::mergeFluentsNext);
    }

    private void mergeFluentsNext(Entity e) {
        Entity current = e;
        while(current != null){
            if(!current.hasSingleChild()){
                // current is a multiChildNode.
                List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();

                // sort the next fluents into groups according to shared entities
                Map<String, List<Fluent>> commonFluentGroups = nextEntities.stream()
                        .filter(next -> next.getType() == EntityType.FLUENT)
                        .map(fluent -> (Fluent) fluent)
                        .collect(Collectors.groupingBy(Fluent::getNameWithoutNeg));

                // if a group has more than one entity, then merge & group their children
                commonFluentGroups.forEach((name, group) -> {
                    if (group.size() > 1) {
                        // create a separate fluent
                        Fluent mergedFluent = (Fluent) createEntityFor(EntityType.FLUENT, name);

                        // for a fluent f, assume there are only f and !f (single instances) since common paths merged already
                        group.forEach(f -> {
                            Entity fluentNext = f.getNext();
                            nextEntities.remove(f);
                            if(f.getName().equals(f.getNameWithoutNeg())){
                                mergedFluent.setNext(fluentNext);
                            } else {
                                mergedFluent.setFalseNext(fluentNext);
                            }
                        });
                        nextEntities.add(mergedFluent);
                    }
                });
                nextEntities.forEach(this::mergeFluentsNext);
            } else if (current.getType() == EntityType.FLUENT){
                // for a fluent, visit FalseNext (trueNext is taken care of by the loop)
                mergeFluentsNext(((Fluent) current).getFalseNext());
            }

            current = current.getNext();
        }
    }

    private void removeRedundantMultiChildEntity(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::removeRedundantNext);

        rootMap.entrySet().stream()
                .filter(entry -> !entry.getValue().hasSingleChild() && ((MultiChildEntity) entry.getValue()).getNextEntities().size() == 1)
                .forEach(entry -> rootMap.put(entry.getKey(), ((MultiChildEntity) entry.getValue()).getNextEntities().get(0)));
    }
    private void removeRedundantNext(Entity e) {
        Entity currEntity = e;
        Entity nextEntity;
        while(currEntity != null){
            if(!currEntity.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) currEntity).getNextEntities();
                nextEntities.forEach(this::removeRedundantNext);
            } else if(currEntity.getType() == EntityType.FLUENT){
                Fluent currFluent = (Fluent) currEntity;
                Entity falseNext = currFluent.getFalseNext();
                if(falseNext != null && !falseNext.hasSingleChild()){
                    List<Entity> nextEntities = ((MultiChildEntity) falseNext).getNextEntities();
                    if(nextEntities.size() == 1){ //TODO PartialOrder entity's getNext may be lost
                        currFluent.setFalseNext(nextEntities.get(0));
                    } else if (nextEntities.size() == 0){
                        currFluent.setFalseNext(null);
                    }
                }
                removeRedundantNext(falseNext);
            }
            // if the next entity is a multiChildEntity, and is redundant, remove it.
            nextEntity = currEntity.getNext();
            if(nextEntity != null && !nextEntity.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) nextEntity).getNextEntities();
                if(nextEntities.size() == 1){ //TODO PartialOrder entity's getNext may be lost
                    currEntity.setNext(nextEntities.get(0));
                } else if (nextEntities.size() == 0){
                    currEntity.setNext(null);
                }
            }
            currEntity = currEntity.getNext();
        }
    }

    private void checkAndRemove(Entity currEntity, Entity nextEntity) {

    }

    private void flipNegatedFluents(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::flipNegatedFluentsNext);
    }

    private void flipNegatedFluentsNext(Entity e) {
        Entity current = e;
        while(current != null){
            // if the current entity is a multiChildEntity, go through its children
            if(!current.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();
                nextEntities.forEach(this::flipNegatedFluentsNext);
            } else if(current.getType() == EntityType.FLUENT){
                Fluent currentFluent = (Fluent) current;
                Entity trueNext = currentFluent.getNext();
                Entity falseNext = currentFluent.getFalseNext();
                if(!currentFluent.getNameWithoutNeg().equals(current.getName())) {
                    // change the fluent's name and set FalseNext
                    current.setName(currentFluent.getNameWithoutNeg());
                    currentFluent.setFalseNext(trueNext);
                    currentFluent.setNext(falseNext);
                    // proceed with the rest of the path
                    flipNegatedFluentsNext(trueNext);
                } else {
                    // proceed with the rest of the path
                    flipNegatedFluentsNext(falseNext);
                }
            }
            current = current.getNext();
        }
    }

    private void addGoalDefinitions(Map<String, Entity> rootMap) {
        // go through each root of reactive rules and add goal definitions
        rootMap.values().forEach(this::addGoalDefinitions);
    }

    private void addGoalDefinitions(Entity e) {
        Entity current = e;
        while(current != null){
            // cover all other paths
            if (!current.hasSingleChild()){
                ((MultiChildEntity) current).getNextEntities().forEach(this::addGoalDefinitions);
            } else if(current.getType() == EntityType.FLUENT){
                addGoalDefinitions(((Fluent)current).getFalseNext());
            }

            Entity goalDef = goalsRootMap.get(current.getName());
            // if definition exists for the entity
            if(goalDef != null){
                // if definition already added
                if(current.hasDefinition()){
                    // if multiple definitions already exist
                    Entity existingGoalDef = current.getDefinition();
                    if(existingGoalDef.getType() == EntityType.OR){
                        ((Or) existingGoalDef).getNextEntities().add(goalDef);
                    } else {
                        ArrayList<Entity> entities = new ArrayList<>();
                        entities.add(existingGoalDef);
                        entities.add(goalDef);
                        Or or = new Or(entities);
                        current.setDefinition(or);
                    }
                } else {
                    current.setDefinition(goalDef);
                }
            }
            // continue along the path
            current = current.getNext();
        }
    }

    private Fluent getReplacementFluent(Entity oldEntity) {
        Fluent fluent = new Fluent(oldEntity.getName());
        fluent.setNext(oldEntity.getNext());
        return fluent;
    }

    private Entity createMultiChildEntityFor(EntityType type, List<Entity> nextEntities) {
        switch(type){
            case AND:
                return new And(nextEntities);
            case OR:
                return new Or(nextEntities);
            case PARTIAL_ORDER:
                return new PartialOrder(nextEntities);
            default:
                return null;
        }
    }

    private Entity createEntityFor(EntityType type, String name) {
        switch (type){
            case ACTION:
                return new Action(name);
            case FLUENT:
                return new Fluent(name);
            case CONCURRENT:
                String[] split = name.split(":");
                return new Concurrent(split[0], split[1]);
            case EXIT:
                return new Exit();
            default:
                return null;
        }
    }

    //TODO use more sophisticated logic to cover complex situations
    private boolean isFluent(String name) {
        return fluents.contains(name)
                || (name.contains("!") && fluents.contains(name.substring(1))) // negation
                || (name.contains(":")); // concurrent
    }

    public Map<String, Entity> getReactiveRulesRootMap() {
        return reactiveRulesRootMap;
    }

}
