package com.LPSWorkflow.model.domainTheory;

import java.util.List;

/**
 * Postcondition (for initiates and terminates)
 */
public interface Postcondition {
    public abstract String getHead();
    public abstract List<String> getBody();
    public abstract PostconditionType getType();
}
