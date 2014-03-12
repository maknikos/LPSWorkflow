package com.LPSWorkflow.model.execution;

import com.LPSWorkflow.model.abstractComponent.Entity;

/**
 * Agent responsible for execution along a single path
 */
public class ExecAgent {
    private Entity CurrentEntity;
    private boolean isReady;
    private int cycleNum;

    public ExecAgent(Entity currentEntity) {
        CurrentEntity = currentEntity;
        isReady = false;
        cycleNum = 0;
    }

    public void increment(){
        cycleNum++;
    }

    public Entity getCurrentEntity() {
        return CurrentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        CurrentEntity = currentEntity;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }
}
