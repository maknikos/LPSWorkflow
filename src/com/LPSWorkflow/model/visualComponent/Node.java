package com.LPSWorkflow.model.visualComponent;

import javafx.scene.Parent;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends Parent {
    private String name;
    protected final double width = 110;
    protected final double height = 70;

    protected Node(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
