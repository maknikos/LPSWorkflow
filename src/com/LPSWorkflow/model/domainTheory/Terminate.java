package com.LPSWorkflow.model.domainTheory;

import com.LPSWorkflow.model.abstractComponent.Entity;

import java.util.List;

/**
 * Post-condition (terminates)
 */
public class Terminate implements Postcondition{
    private Entity head;
    private List<Entity> body;

    public Terminate(Entity head, List<Entity> body) {
        this.head = head;
        this.body = body;
    }

    public Entity getHead() {
        return head;
    }

    @Override
    public List<Entity> getBody() {
        return body;
    }

    @Override
    public PostconditionType getType() {
        return PostconditionType.TERMINATES;
    }
}
