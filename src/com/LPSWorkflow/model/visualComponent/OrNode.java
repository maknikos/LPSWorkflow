package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public class OrNode extends MultiChildNode {
    public OrNode() {
        super("OR");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public EntityType getType() {
        return EntityType.OR;
    }
}
