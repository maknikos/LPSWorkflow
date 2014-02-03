package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.Choice;
import com.LPSWorkflow.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds structure of connected entities from 'connections' context maps
 */
public class StructureBuilder {
    private List<Entity> rootList;
    private Map<String, Entity> rootMap;

    public StructureBuilder() {
        this.rootList = new ArrayList<Entity>();
        this.rootMap = new HashMap<String, Entity>();
    }

    public void build(Map<Object, Object> reactiveRuleConnections, Map<Object, Object> goalConnections){
        // find roots (nothing points to a root)
        for(Object key : reactiveRuleConnections.keySet()){
            if(!reactiveRuleConnections.containsValue(key)){
                rootList.add((Entity) key);
            }
        }

        // Build connections of entities
        for(Entity root : rootList){
            connectEntities(root, reactiveRuleConnections);
        }

        // Merge common roots
        for(Entity root : rootList){
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
