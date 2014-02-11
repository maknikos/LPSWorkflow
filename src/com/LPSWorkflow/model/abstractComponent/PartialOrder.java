package com.LPSWorkflow.model.abstractComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * PartialOrder entity that groups together partially ordered actions/conditions
 */
public class PartialOrder extends Entity {
    //TODO currently only supports an Atom on either side of '||'
    private List<Entity> entities;

    public PartialOrder(String first, String second) {
        super(first + "||" + second);
        entities = new ArrayList<Entity>();
        entities.add(new Action(first));
        entities.add(new Action(second));
    }

    public List<Entity> getEntities(){
        return entities;
    }
}
