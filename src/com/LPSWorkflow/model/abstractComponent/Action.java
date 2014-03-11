package com.LPSWorkflow.model.abstractComponent;

/**
 * Action entity (a box)
 */
public class Action extends Entity {
    public Action(String name) {
        super(name);
    }

    @Override
    public EntityType getType() {
        return EntityType.ACTION;
    }
}
