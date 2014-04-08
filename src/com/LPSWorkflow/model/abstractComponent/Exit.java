package com.LPSWorkflow.model.abstractComponent;

/**
 * Action entity (a box)
 */
public class Exit extends Entity {
    public Exit() {
        super("Exit");
    }

    @Override
    public EntityType getType() {
        return EntityType.EXIT;
    }
}
