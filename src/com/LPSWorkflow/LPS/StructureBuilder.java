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

    public StructureBuilder() {
        this.reactiveRulesRootMap = new HashMap<>();
        this.goalsRootMap = new HashMap<>();
    }

    public void build(Map<Object, Object> reactiveRuleRoots,
                      Map<Object, Object> reactiveRuleConnections,
                      Map<Object, Object> goalRoots,
                      Map<Object, Object> goalConnections,
                      List<String> fluents)
    {
        buildChains(reactiveRulesRootMap, reactiveRuleRoots, reactiveRuleConnections, true);
        buildChains(goalsRootMap, goalRoots, goalConnections, false);

        // distinguish fluents from actions by replacing corresponding Action with Fluent object
        replaceFluents(reactiveRulesRootMap, fluents);
        replaceFluents(goalsRootMap, fluents);

        // merge common starts of multiChildEntities (e.g. OR)
        mergeCommonPaths(reactiveRulesRootMap);
        mergeCommonPaths(goalsRootMap);

        // merge fluents and their negations
        mergeFluents(reactiveRulesRootMap);
        mergeFluents(goalsRootMap);

        //flip the sign of negated fluents and use FalseNext
        flipNegatedFluents(reactiveRulesRootMap);
        flipNegatedFluents(goalsRootMap);

        addGoalDefinitions();
    }

    private void flipNegatedFluents(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::flipNegatedFluentsNext);
    }

    private void flipNegatedFluentsNext(Entity e) {
        if(e == null){
            return;
        }

        // jump to Fluent
        Entity current = e;

        // skip through the path until there is a negated fluent
        while(current != null && current.getType() != EntityType.FLUENT){
            // if the current entity is a multiChildEntity, go through its children
            if(!current.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();
                nextEntities.forEach(this::flipNegatedFluentsNext);
            }
            current = current.getNext();
        }

        // no fluent in the path.
        if(current == null){
            return;
        }

        Fluent currentFluent = (Fluent) current;
        Entity trueNext = currentFluent.getNext();
        Entity falseNext = currentFluent.getFalseNext();
        if(!currentFluent.getNameWithoutNeg().equals(current.getName())) {
            // change the fluent's name and set FalseNext
            current.setName(currentFluent.getNameWithoutNeg());
            currentFluent.setFalseNext(trueNext);
            currentFluent.setNext(falseNext);
        }
        // proceed with the rest of the path
        flipNegatedFluentsNext(trueNext);
        flipNegatedFluentsNext(falseNext);
    }

    private void mergeFluents(Map<String, Entity> rootMap) {
        rootMap.values().forEach(this::mergeFluentsNext);
    }

    private void mergeFluentsNext(Entity e) {
        if(e == null){
            return;
        }

        // jump to multiChildNode
        Entity prev = e;
        Entity current = e.getNext();
        while(current != null && current.hasSingleChild()){
            prev = current;
            current = current.getNext();
        }

        if(current == null){
            return;
        }

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
                    Entity memberNext = f.getNext();
                    nextEntities.remove(f);
                    if(f.getName().equals(f.getNameWithoutNeg())){
                        mergedFluent.setNext(memberNext);
                    } else {
                        mergedFluent.setFalseNext(memberNext);
                    }
                });

                nextEntities.add(mergedFluent);
                // repeat until the end reached, or no more common fluents.
                mergeFluentsNext(mergedFluent);
            }
        });

        // if current multiChildEntity has only one child, then connect directly to its child
        if(nextEntities.size() == 1){
            prev.setNext(nextEntities.get(0));
        } else {
            nextEntities.forEach(this::mergeFluentsNext);
        }

        // proceed with the trailing path
        mergeFluentsNext(current.getNext());
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
                    Entity mergedEntity = createEntityFor(entity.getType(), entity.getName());

                    group.forEach(nextEntities::remove);
                    List<Entity> newNextEntities = group.stream().filter(Entity::hasNext)
                            .map(Entity::getNext).collect(Collectors.toList());
                    Entity nextMultiChildEntity = createMultiChildEntityFor(currentMultiType, newNextEntities);
                    mergedEntity.setNext(nextMultiChildEntity);

                    nextEntities.add(mergedEntity);

                    // repeat until the end reached, or no more common paths.
                    // note: we don't have to go through all nextEntities, since we are merging 'common beginnings'
                    mergeCommonPathsNext(mergedEntity);
                });
            }

            // continue through the path (there is no FalseNext used for Fluents yet, so just use getNext())
            current = current.getNext();
        }
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

    private void replaceFluents(Map<String, Entity> rootMap, List<String> fluents) {
        List<String> affectedKeys = rootMap.entrySet().stream()
                .filter(entry -> isFluent(entry.getValue().getName(), fluents))
                .map(Map.Entry<String, Entity>::getKey).collect(Collectors.toList());
        rootMap.forEach((s, e) -> replaceFluentsNext(e, fluents));

        affectedKeys.forEach(k -> {
            rootMap.put(k, getReplacementFluent(rootMap.get(k)));
        });
    }

    private void replaceFluentsNext(Entity e, List<String> fluents) {
        // replace the next entity or the "nextEntities"
        if(e == null){
            return;
        }

        // in case the current entity has multiple children
        if(!e.hasSingleChild()){
            List<Entity> nextEntities = ((MultiChildEntity) e).getNextEntities();

            List<Entity> affectedNextEntities = nextEntities.stream()
                    .filter(next -> isFluent(next.getName(), fluents)).collect(Collectors.toList());
            nextEntities.forEach(next -> replaceFluentsNext(next, fluents));

            // change the fluent roots of next paths
            affectedNextEntities.forEach(affected -> {
                nextEntities.remove(affected);
                nextEntities.add(getReplacementFluent(affected));
            });
        }

        Entity nextEntity = e.getNext();
        // replace next entity if applicable
        if(nextEntity != null && isFluent(nextEntity.getName(), fluents)){
            Fluent fluent = getReplacementFluent(nextEntity);
            e.setNext(fluent);
            replaceFluentsNext(fluent, fluents);
        } else {
            replaceFluentsNext(nextEntity, fluents);
        }
    }

    private Fluent getReplacementFluent(Entity oldEntity) {
        Fluent fluent = new Fluent(oldEntity.getName());
        fluent.setNext(oldEntity.getNext());
        return fluent;
    }

    //TODO use more sophisticated logic to cover complex situations
    private boolean isFluent(String name, List<String> fluents) {
        return fluents.contains(name)
                || (name.contains("!") && fluents.contains(name.substring(1))) // negation
                || (name.contains(":")); // concurrent
    }

    private void addGoalDefinitions() {
        // go through each root of reactive rules and add goal definitions
        reactiveRulesRootMap.values().forEach(this::addGoalDefinitions);
        //goalsRootMap.values().forEach(this::addGoalDefinitions);
    }

    private void addGoalDefinitions(Entity e) {
        Entity goalDef = goalsRootMap.get(e.getName());
        // if definition exists for the entity
        if(goalDef != null){
            // if definition already added
            if(e.hasDefinition()){
                // if multiple definitions already exist
                Entity existingGoalDef = e.getDefinition();
                if(existingGoalDef.getType() == EntityType.OR){
                    ((Or) existingGoalDef).getNextEntities().add(goalDef);
                } else {
                    ArrayList<Entity> entities = new ArrayList<>();
                    entities.add(existingGoalDef);
                    entities.add(goalDef);
                    Or or = new Or(entities);
                    e.setDefinition(or);
                }
            } else {
                e.setDefinition(goalDef);
            }
        }

        if(e.hasNext()){
            addGoalDefinitions(e.getNext());
        } else if (!e.hasSingleChild()){
            ((MultiChildEntity) e).getNextEntities().forEach(this::addGoalDefinitions);
        }
    }

    private void buildChains(Map<String, Entity> rootMap,
                             Map<Object, Object> ruleRoots, Map<Object, Object> ruleConnections,
                             boolean groupByAnd) {
        // Build connections of entities
        ruleRoots.forEach((root, next) -> {
            // connect Antecedent to the beginning of the Consequent
            ((Entity)root).setNext((Entity) next);
            // connect the rest
            connectEntities((Entity)next, ruleConnections);
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

    public Map<String, Entity> getReactiveRulesRootMap() {
        return reactiveRulesRootMap;
    }

    private void connectEntities(Entity e, Map<Object, Object> connections) {
        if(e == null){
            return;
        }
        Entity entity = (Entity) connections.get(e);

        if(entity == null){
            //there is no next entity for e. Exit.
            e.setNext(new Exit());
        } else {
            e.setNext(entity);
            connectEntities(entity, connections);
        }
    }
}
