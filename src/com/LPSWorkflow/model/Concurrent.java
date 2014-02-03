package com.LPSWorkflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Actions/Conditions that must hold at the same time
 */
public class Concurrent extends Entity {
    //TODO currently only supports an Atom on either side of ':'
    private List<Entity> entities;

    public Concurrent(String first, String second) {
        super(first + ":" + second);
        entities = new ArrayList<Entity>();
        entities.add(new Action(first));
        entities.add(new Action(second));
    }

    public List<Entity> getEntities(){
        return entities;
    }
}
