package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * Entity to represent multiple choices or options
 */
public class Or extends MultiChildEntity {
    public Or(List<Entity> entities) {
        super("OR", entities);
    }
}
