package com.LPSWorkflow.model.domainTheory;

import com.LPSWorkflow.model.abstractComponent.Entity;

import java.util.List;

/**
 * Precondition containing conflicting entities
 */
public class Precondition {
    List<Entity> conflictingEntities;

    public Precondition(List<Entity> entities) {
        conflictingEntities = entities;
    }

    public List<Entity> getConflictingEntities() {
        return conflictingEntities;
    }
}
