package com.LPSWorkflow.model.abstractComponent;

import com.LPSWorkflow.common.EntityType;

import java.util.List;

/**
 * Entity to represent multiple choices or options
 */
public class Or extends MultiChildEntity {
    public Or(List<Entity> entities) {
        super("OR", entities);
    }

    @Override
    public EntityType getType() {
        return EntityType.OR;
    }
}
