package com.LPSWorkflow.model.abstractComponent;

import com.LPSWorkflow.common.EntityType;

/**
 * An abstract entity in the workflow diagram
 */
public abstract class Entity {
    private String name;
    private Entity next;
    private Entity definition;

    public Entity getDefinition() {
        return definition;
    }

    public void setDefinition(Entity definition) {
        this.definition = definition;
    }

    public boolean hasDefinition(){
        return definition != null;
    }

    public Entity(String name) {
        this.name = name;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Entity getNext() {
        return next;
    }

    public void setNext(Entity next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract EntityType getType();

    public String toString(){
        return name;
    }

    public boolean hasSingleChild(){
        return true;
    }
}