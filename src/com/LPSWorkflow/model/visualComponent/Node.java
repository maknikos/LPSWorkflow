package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.scene.layout.Region;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends Region {
    private String name;
    protected final double width = Constants.NODE_WIDTH;
    protected final double height = Constants.NODE_HEIGHT;

    protected Node(String name) {
        this.name = name;
        setStyle("-fx-background-color:white;");
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
