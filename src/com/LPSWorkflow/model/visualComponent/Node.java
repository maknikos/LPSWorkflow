package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends Parent {
    private String name;
    protected final double width = Constants.NODE_WIDTH;
    protected final double height = Constants.NODE_HEIGHT;

    protected Node(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void relocate(double v, double v2) {
        super.relocate(v, v2); // TODO remove if redundant
    }

    @Override
    public void resize(double v, double v2) {
        super.resize(v, v2);
    }
}
