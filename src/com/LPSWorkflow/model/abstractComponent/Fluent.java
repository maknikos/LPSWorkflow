package com.LPSWorkflow.model.abstractComponent;

/**
 * Fluent entity (diamond)
 */
public class Fluent extends Entity {
    private Entity FalseNext; // next path when the fluent is false (does not hold)

    public Fluent(String name) {
        super(name);
    }

    public Entity getFalseNext() {
        return FalseNext;
    }

    public void setFalseNext(Entity falseNext) {
        FalseNext = falseNext;
    }

    public String getNameWithoutNeg(){
        String name = getName();
        if(name.contains("!")){
            return getName().split("!")[1];
        } else {
            return getName();
        }
    }

    @Override
    public EntityType getType() {
        return EntityType.FLUENT;
    }
}
