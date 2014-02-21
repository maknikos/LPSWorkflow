package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.abstractComponent.And;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.abstractComponent.Or;

import java.util.ArrayList;
import java.util.HashMap;
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
                      Map<Object, Object> goalConnections)
    {
        buildReactiveRuleChains(reactiveRulesRootMap, reactiveRuleRoots, reactiveRuleConnections);
        buildGoalChains(goalsRootMap, goalRoots, goalConnections);
        addGoalDefinitions();
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
                if(isOr(existingGoalDef)){
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
        } else if (isOr(e) || isAnd(e)){
            for(Entity child : ((MultiChildEntity) e).getNextEntities()){
                addGoalDefinitions(child);
            }
        }
    }

    private void buildReactiveRuleChains(Map<String, Entity> rootMap,
                                         Map<Object, Object> ruleRoots, Map<Object, Object> ruleConnections) {
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
                // Same root already exists. Merge the reactive rule roots by AND.
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
                    And and = new And(entities);
                    existingRoot.setNext(and);
                }
            } else {
                rootMap.put(rootName, root);
            }
        }
    }

    private void buildGoalChains(Map<String, Entity> rootMap,
                                 Map<Object, Object> goalRoots, Map<Object, Object> goalConnections) {
        // Build connections of entities
        for(Object root : goalRoots.keySet()){
            // connect Antecedent to the beginning of the Consequent
            Entity next = (Entity) goalRoots.get(root);
            ((Entity)root).setNext(next);
            // connect the rest
            connectEntities(next, goalConnections);
        }

        for(Object rootObj : goalRoots.keySet()){
            Entity root = (Entity) rootObj;
            String rootName = root.getName();
            if(rootMap.containsKey(rootName)){
                // Same root already exists. Merge the goal roots by OR.
                Entity existingRoot = rootMap.get(rootName);
                Entity existingNext = existingRoot.getNext(); // Either the next entity or an OR

                if(root.getNext() == null) {
                    // if it does not have next entity, ignore it
                    break;
                } else if(!existingNext.hasSingleChild()) {
                    // if the next is already multiChildEntity, add to its children
                    ((MultiChildEntity)existingNext).getNextEntities().add(root.getNext());
                } else {
                    // if the next is a singleChildEntity, create OR entity and add
                    ArrayList<Entity> entities = new ArrayList<Entity>();
                    entities.add(existingNext);
                    entities.add(root.getNext());
                    Or or = new Or(entities);
                    existingRoot.setNext(or);
                }
            } else {
                rootMap.put(rootName, root);
            }
        }
    }

    public Map<String, Entity> getReactiveRulesRootMap() {
        return reactiveRulesRootMap;
    }

    private void connectEntities(Entity e, Map<Object, Object> reactiveRuleConnections) {
        if(e == null){
            return;
        }
        Entity entity = (Entity) reactiveRuleConnections.get(e);
        e.setNext(entity);
        connectEntities(entity, reactiveRuleConnections);
    }

    private boolean isOr(Entity e) {
        return e.getName().equals("OR");
    }

    private boolean isAnd(Entity e) {
        return e.getName().equals("AND");
    }
}
