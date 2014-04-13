package com.LPSWorkflow.model.execution;

import com.LPSWorkflow.model.abstractComponent.Entity;

/**
 * Agent responsible for execution along a single path
 */
public class ExecAgent {
    private Entity currentEntity;
    private boolean isReady;
    private int cycleNum;

    public ExecAgent(Entity currentEntity) {
        this.currentEntity = currentEntity;
        isReady = false;
        cycleNum = 0;
    }

    public void increment(){
        cycleNum++;
    }

    public Entity getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    public int getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(int cycleNum) {
        this.cycleNum = cycleNum;
    }

    @Override
    public ExecAgent clone(){
        ExecAgent clone = new ExecAgent(currentEntity);
        clone.setReady(isReady);
        clone.setCycleNum(cycleNum);
        return clone;
    }
}
