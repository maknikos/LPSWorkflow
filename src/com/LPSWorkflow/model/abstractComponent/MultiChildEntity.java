package com.LPSWorkflow.model.abstractComponent;

import java.util.List;

/**
 * Entities with multiple children
 */
public abstract class MultiChildEntity extends Entity {
    protected List<Entity> entities;

    public MultiChildEntity(String name, List<Entity> entities) {
        super(name);
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public int getChildCount() {
        if(entities == null){
            return 0;
        } else {
            return entities.size();
        }
    } //TODO using this to distinguish multi-child entities from others
        // relies on there being more than 1 child ... which must hold, but not enforced in the program.
}
