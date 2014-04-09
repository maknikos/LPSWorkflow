package com.LPSWorkflow.model.abstractComponent;

import com.LPSWorkflow.common.EntityType;

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
