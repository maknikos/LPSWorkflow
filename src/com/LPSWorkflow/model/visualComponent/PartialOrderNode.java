package com.LPSWorkflow.model.visualComponent;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public class PartialOrderNode extends MultiChildNode {
    public PartialOrderNode() {
        super("||");
    }

    @Override
    public String getName() {
        return null;
    }
}
