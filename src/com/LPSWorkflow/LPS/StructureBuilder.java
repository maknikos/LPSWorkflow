package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.abstractComponent.Choice;
import com.LPSWorkflow.model.abstractComponent.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Builds structure of connected entities from 'connections' context maps
 */
public class StructureBuilder {
    private Map<String, Entity> reactiveRulesRootMap;
    private Map<String, Entity> goalsRootMap;

    public StructureBuilder() {
        this.reactiveRulesRootMap = new HashMap<String, Entity>();
        this.goalsRootMap = new HashMap<String, Entity>();
    }

    public void build(Map<Object, Object> reactiveRuleRoots,
                      Map<Object, Object> reactiveRuleConnections,
                      Map<Object, Object> goalRoots,
                      Map<Object, Object> goalConnections)
    {
        buildChains(reactiveRulesRootMap, reactiveRuleRoots, reactiveRuleConnections);
        buildChains(goalsRootMap, goalRoots, goalConnections);
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
                if(isChoice(existingGoalDef)){
                    ((Choice) existingGoalDef).getEntities().add(goalDef);
                } else {
                    ArrayList<Entity> entities = new ArrayList<Entity>();
                    entities.add(existingGoalDef);
                    entities.add(goalDef);
                    Choice choice = new Choice(entities);
                    e.setDefinition(choice);
                }
            } else {
                e.setDefinition(goalDef);
            }
        }

        if(e.hasNext()){
            addGoalDefinitions(e.getNext());
        } else if (isChoice(e)){
            for(Entity child : ((Choice) e).getEntities()){
                addGoalDefinitions(child);
            }
        }
    }

    private void buildChains(Map<String, Entity> rootMap, Map<Object, Object> goalRoots, Map<Object, Object> goalConnections) {
        // Build connections of entities
        for(Object root : goalRoots.keySet()){
            // connect Antecedent to the beginning of the Consequent
            Entity next = (Entity) goalRoots.get(root);
            ((Entity)root).setNext(next);
            // connect the rest
            connectEntities(next, goalConnections);
        }

        // Merge common roots
        for(Object rootObj : goalRoots.keySet()){
            Entity root = (Entity) rootObj;
            String rootName = root.getName();
            if(rootMap.containsKey(rootName)){
                Entity existingRoot = rootMap.get(rootName);
                Entity existingNext = existingRoot.getNext();
                if(existingNext == null){
                    // no next element for the node, so just replace
                    rootMap.put(rootName, root);
                } else if(root.getNext() == null) {
                    break;
                } else if(isChoice(existingNext)) {
                    //Choice already exists, so just add the current to it.
                    ((Choice)existingNext).getEntities().add(root);
                } else {
                    // Otherwise, make a choice and add the next entities as its children
                    ArrayList<Entity> entities = new ArrayList<Entity>();
                    entities.add(existingNext);
                    entities.add(root.getNext());
                    Choice choice = new Choice(entities);
                    existingRoot.setNext(choice);
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

    private boolean isChoice(Entity e) {
        return e.getName().equals("OR");
    }
}
