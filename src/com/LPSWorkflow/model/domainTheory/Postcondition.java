package com.LPSWorkflow.model.domainTheory;

import com.LPSWorkflow.model.abstractComponent.Entity;

import java.util.List;

/**
 * Postcondition (for initiates and terminates)
 */
public interface Postcondition {
    public abstract Entity getHead();
    public abstract List<Entity> getBody();
    public abstract PostconditionType getType();
}
