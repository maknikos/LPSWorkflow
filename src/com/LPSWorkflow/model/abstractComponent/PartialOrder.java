package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * PartialOrder entity that groups together partially ordered actions/conditions
 */
public class PartialOrder extends MultiChildEntity {
    //TODO currently only supports an Atom on either side of '||'

    public PartialOrder(List entities) {
        super("||", entities);
    }

    @Override
    public EntityType getType() {
        return EntityType.PARTIAL_ORDER;
    }
}
