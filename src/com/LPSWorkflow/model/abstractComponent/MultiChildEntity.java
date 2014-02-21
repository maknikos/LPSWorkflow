package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * Entities with multiple children
 */
public abstract class MultiChildEntity extends Entity {
    protected List<Entity> nextEntities;

    public MultiChildEntity(String name, List<Entity> nextEntities) {
        super(name);
        this.nextEntities = nextEntities;
    }

    public List<Entity> getNextEntities() {
        return nextEntities;
    }

    @Override
    public boolean hasSingleChild() {
        return false;
    }
}
