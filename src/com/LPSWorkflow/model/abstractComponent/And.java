package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * Entity to represent multiple paths which all must hold
 */
public class And extends MultiChildEntity {
    public And(List<Entity> entities) {
        super("AND", entities);
    }
}