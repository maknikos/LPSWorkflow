package com.LPSWorkflow.model;

/**
 * An entity in the workflow diagram
 */
public abstract class Entity {
    private String name;
    private Entity next;

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
}