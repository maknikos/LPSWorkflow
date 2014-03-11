package com.LPSWorkflow.model.abstractComponent;

/**
 * Fluent entity (diamond)
 */
public class Fluent extends Entity{
    public Fluent(String name) {
        super(name);
    }

    @Override
    public EntityType getType() {
        return EntityType.FLUENT;
    }
}
