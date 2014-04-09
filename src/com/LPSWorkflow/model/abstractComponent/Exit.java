package com.LPSWorkflow.model.abstractComponent;

import com.LPSWorkflow.common.EntityType;

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
