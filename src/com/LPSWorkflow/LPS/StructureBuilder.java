package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.Choice;
import com.LPSWorkflow.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Builds structure of connected entities from 'connections' context maps
 */
public class StructureBuilder {
    private Map<String, Entity> rootMap;

    public StructureBuilder() {
        this.rootMap = new HashMap<String, Entity>();
    }

    public void build(Map<Object, Object> reactiveRuleRoots,
                      Map<Object, Object> reactiveRuleConnections,
                      Map<Object, Object> goalRoots,
                      Map<Object, Object> goalConnections) {
        buildReactiveRules(reactiveRuleRoots, reactiveRuleConnections);
        buildGoalInformation(goalRoots, goalConnections);
    }

    private void buildGoalInformation(Map<Object, Object> goalRoots, Map<Object, Object> goalConnections) {

    }

    private void buildReactiveRules(Map<Object, Object> reactiveRuleRoots, Map<Object, Object> reactiveRuleConnections) {
        // Build connections of entities
        for(Object root : reactiveRuleRoots.keySet()){
            // connect Antecedent to the beginning of the Consequent
            Entity next = (Entity) reactiveRuleRoots.get(root);
            ((Entity)root).setNext(next);
            // connect the rest
            connectEntities(next, reactiveRuleConnections);
        }

        // Merge common roots
        for(Object rootObj : reactiveRuleRoots.keySet()){
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
                } else if(existingNext.getName().equals("OR")) { //TODO make OR a constant?
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

    private void connectEntities(Entity e, Map<Object, Object> reactiveRuleConnections) {
        if(e == null){
            return;
        }
        Entity entity = (Entity) reactiveRuleConnections.get(e);
        e.setNext(entity);
        connectEntities(entity, reactiveRuleConnections);
    }

    public Map<String, Entity> getRootMap() {
        return rootMap;
    }
}
