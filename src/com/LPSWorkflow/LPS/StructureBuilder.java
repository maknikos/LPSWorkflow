package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.abstractComponent.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds structure of connected entities from 'connections' context maps
 */
public class StructureBuilder {
    private Map<String, Entity> reactiveRulesRootMap;
    private Map<String, Entity> goalsRootMap; //TODO make use of this from visualiser?

    public StructureBuilder() {
        this.reactiveRulesRootMap = new HashMap<String, Entity>();
        this.goalsRootMap = new HashMap<String, Entity>();
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
        for(Entity e : rootMap.values()){
            flipNegatedFluentsNext(e);
        }
    }

    private void flipNegatedFluentsNext(Entity e) {
        if(e == null){
            return;
        }

        // jump to Fluent
        Entity prev = e;
        Entity current = e.getNext();

        // skip through the path until there is a negated fluent
        while(current != null
                && (current.getType() != EntityType.FLUENT || ((Fluent)current).getNameWithoutNeg().equals(current.getName()))){
            // if the current entity is a multiChildEntity, go through its children
            if(!current.hasSingleChild()){
                List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();
                for(Entity next : nextEntities){
                    flipNegatedFluentsNext(next);
                }
            }
            prev = current;
            current = current.getNext();
        }

        // no fluent in the path.
        if(current == null){
            return;
        }

        // make a new Fluent with FalseNext as the current fluent's Next
        String name = ((Fluent)current).getNameWithoutNeg();
        Entity next = current.getNext();
        Fluent newFluent = new Fluent(name);
        newFluent.setFalseNext(next);
        prev.setNext(newFluent);

        // proceed with the rest of the path
        flipNegatedFluentsNext(next);
    }

    private void mergeFluents(Map<String, Entity> rootMap) {
        for(Entity e : rootMap.values()){
            mergeFluentsNext(e);
        }
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
        Map<String, List<Fluent>> commonFluentGroups = new HashMap<String, List<Fluent>>();
        for(Entity next : nextEntities){
            if(next.getType() == EntityType.FLUENT) {
                Fluent nextFluent = (Fluent) next;
                String name = nextFluent.getNameWithoutNeg();
                if(commonFluentGroups.containsKey(name)){
                    commonFluentGroups.get(name).add(nextFluent);
                } else {
                    ArrayList<Fluent> fluents = new ArrayList<Fluent>();
                    fluents.add(nextFluent);
                    commonFluentGroups.put(name, fluents);
                }
            }
        }

        // if a group has more than one entity, then merge & group their children
        for(String key : commonFluentGroups.keySet()){
            List<Fluent> group = commonFluentGroups.get(key);
            if (group.size() > 1) {
                // create a separate fluent
                Fluent mergedFluent = (Fluent) createEntityFor(EntityType.FLUENT, key);

                // for a fluent f, assume there are only f and !f (single instances) since common paths merged already
                for(Fluent member : group){
                    Entity memberNext = member.getNext();
                    nextEntities.remove(member);
                    if(member.getName().equals(member.getNameWithoutNeg())){
                        mergedFluent.setNext(memberNext);
                    } else {
                        mergedFluent.setFalseNext(memberNext);
                    }
                }
                nextEntities.add(mergedFluent);
                // repeat until the end reached, or no more common fluents.
                mergeFluentsNext(mergedFluent);
            }
        }

        // if current multiChildEntity has only one child, then connect directly to its child
        if(nextEntities.size() == 1){
            prev.setNext(nextEntities.get(0));
        } else {
            for(Entity next : nextEntities){
                mergeFluentsNext(next);
            }
        }

        // proceed with the trailing path
        mergeFluentsNext(current.getNext());
    }

    private void mergeCommonPaths(Map<String, Entity> rootMap) {
        for(Entity e : rootMap.values()){
            mergeCommonPathsNext(e);
        }
    }

    private void mergeCommonPathsNext(Entity e) {
        if(e == null){
            return;
        }

        // jump to multiChildEntity to perform merge
        Entity prev = e;
        Entity current = e.getNext();
        while(current != null && current.hasSingleChild()){
            prev = current;
            current = current.getNext();
        }
        // no multiChildEntity found
        if(current == null){
            return;
        }

        // current is a multiChildNode.
        EntityType currentMultiType = current.getType();
        List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();

        // sort the next paths into groups according to shared entities
        Map<String, List<Entity>> commonStartGroups = new HashMap<String, List<Entity>>();
        for(Entity next : nextEntities){
            String name = next.getName();

            if(commonStartGroups.containsKey(name)){
                commonStartGroups.get(name).add(next);
            } else {
                ArrayList<Entity> entities = new ArrayList<Entity>();
                entities.add(next);
                commonStartGroups.put(name, entities);
            }
        }
        // if a group has more than one entity, then merge & group their children
        for(List<Entity> group : commonStartGroups.values()){
            if(group.size() > 1){
                // create a separate entity
                Entity entity = group.get(0);
                Entity mergedEntity = createEntityFor(entity.getType(), entity.getName());

                // create a multiChildNode to group the next entities
                List<Entity> newNextEntities = new ArrayList<Entity>();
                for(Entity member : group){
                    Entity memberNext = member.getNext();
                    nextEntities.remove(member);
                    if(memberNext != null){
                        newNextEntities.add(memberNext);
                    }
                }
                Entity nextMultiChildEntity = createMultiChildEntityFor(currentMultiType, newNextEntities);

                // set the multiChildNode as the mergedEntity's next
                nextEntities.add(mergedEntity);
                mergedEntity.setNext(nextMultiChildEntity);

                // repeat until the end reached, or no more common paths.
                mergeCommonPathsNext(mergedEntity);
            }
        }

        // if current multiChildEntity has only one child, then connect directly to its child
        if(nextEntities.size() == 1){
            prev.setNext(nextEntities.get(0));
        } else if(nextEntities.size() == 0){
            prev.setNext(null);
        }

        // proceed with the trailing path
        mergeCommonPathsNext(current.getNext());
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
        List<String> affectedKeys = new ArrayList<String>();
        for(String s : rootMap.keySet()){
            Entity e = rootMap.get(s);
            if(isFluent(e.getName(), fluents)){
                affectedKeys.add(s);
            }
            replaceFluentsNext(e, fluents);
        }

        for (String key : affectedKeys){
            Entity oldValue = rootMap.get(key);
            Fluent newValue = getReplacementFluent(oldValue);
            rootMap.remove(key);
            rootMap.put(key, newValue);
        }
    }

    private void replaceFluentsNext(Entity e, List<String> fluents) {
        // replace the next entity or the "nextEntities"
        if(e == null){
            return;
        }

        // in case the current entity has multiple children
        if(!e.hasSingleChild()){
            List<Entity> nextEntities = ((MultiChildEntity) e).getNextEntities();
            List<Entity> affectedNextEntities = new ArrayList<Entity>();
            for(Entity next : nextEntities){
                if(isFluent(next.getName(), fluents)){
                    affectedNextEntities.add(next);
                }
                // recursively replace for each path
                replaceFluentsNext(next, fluents);
            }
            // change the fluent roots of next paths
            for(Entity affected : affectedNextEntities){
                Fluent fluent = getReplacementFluent(affected);
                nextEntities.remove(affected);
                nextEntities.add(fluent);
            }
        }

        Entity nextEntity = e.getNext();
        // replace next entity if applicable
        if(nextEntity != null
                && isFluent(nextEntity.getName(), fluents)){
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
        for(Entity root : reactiveRulesRootMap.values()){
            addGoalDefinitions(root);
        }
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
                    ArrayList<Entity> entities = new ArrayList<Entity>();
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
            for(Entity child : ((MultiChildEntity) e).getNextEntities()){
                addGoalDefinitions(child);
            }
        }
    }

    private void buildChains(Map<String, Entity> rootMap,
                             Map<Object, Object> ruleRoots, Map<Object, Object> ruleConnections,
                             boolean groupByAnd) {
        // Build connections of entities
        for(Object root : ruleRoots.keySet()){
            // connect Antecedent to the beginning of the Consequent
            Entity next = (Entity) ruleRoots.get(root);
            ((Entity)root).setNext(next);
            // connect the rest
            connectEntities(next, ruleConnections);
        }

        // Merge common roots
        for(Object rootObj : ruleRoots.keySet()){
            Entity root = (Entity) rootObj;
            String rootName = root.getName();
            if(rootMap.containsKey(rootName)){
                // Same root already exists. Merge the roots by AND or OR.
                Entity existingRoot = rootMap.get(rootName); //TODO may need to group together the antecedents?
                Entity existingNext = existingRoot.getNext(); // Either the next entity or an AND

                if(root.getNext() == null) {
                    // if it does not have next entity, ignore it
                    break;
                } else if(!existingNext.hasSingleChild()) {
                    // if the next is already multiChildEntity, add to its children
                    ((MultiChildEntity)existingNext).getNextEntities().add(root.getNext());
                } else {
                    // if the next is a singleChildEntity, create AND entity and add
                    ArrayList<Entity> entities = new ArrayList<Entity>();
                    entities.add(existingNext);
                    entities.add(root.getNext());

                    if(groupByAnd){
                        And and = new And(entities);
                        existingRoot.setNext(and);
                    } else {
                        Or or = new Or(entities);
                        existingRoot.setNext(or);
                    }
                }
            } else {
                rootMap.put(rootName, root);
            }
        }
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
