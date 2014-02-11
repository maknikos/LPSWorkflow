package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * Entity to represent multiple choices or options
 */
public class Choice extends Entity {
    private List<Entity> entities;

    public Choice(List<Entity> entities) {
        super("OR");
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
