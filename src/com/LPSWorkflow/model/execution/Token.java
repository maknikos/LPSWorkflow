package com.LPSWorkflow.model.execution;

import com.LPSWorkflow.model.abstractComponent.Entity;

/**
 * Agent responsible for execution along a single path
 */
public class Token {
    private Entity currentEntity;
    private boolean isReady;
    private int tick; // token's own cycle number.

    public Token(Entity currentEntity) {
        this.currentEntity = currentEntity;
        isReady = false;
        tick = 0;
    }

    public void increment(){
        tick++;
    }

    public Entity getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }

    public boolean isReady() {
        return isReady;
    } //TODo used?

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    @Override
    public Token clone(){
        Token clone = new Token(currentEntity);
        clone.setReady(isReady);
        clone.setTick(tick);
        return clone;
    }
}
