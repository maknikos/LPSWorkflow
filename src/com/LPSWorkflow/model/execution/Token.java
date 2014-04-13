package com.LPSWorkflow.model.execution;

import com.LPSWorkflow.model.abstractComponent.Entity;

/**
 * Agent responsible for execution along a single path
 */
public class Token {
    private Entity currentEntity;
    private boolean isReady;
    private int cycleNum;

    public Token(Entity currentEntity) {
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
    public Token clone(){
        Token clone = new Token(currentEntity);
        clone.setReady(isReady);
        clone.setCycleNum(cycleNum);
        return clone;
    }
}
