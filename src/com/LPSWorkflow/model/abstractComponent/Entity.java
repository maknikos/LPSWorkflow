package com.LPSWorkflow.model.abstractComponent;

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

    public String toString(){
        return name;
    }

    public boolean hasSingleChild(){
        return true;
    }
}