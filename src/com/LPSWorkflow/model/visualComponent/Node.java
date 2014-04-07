package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends Region {
    private String name;
    private Label detailsLabel;
    protected final double width = Constants.NODE_WIDTH;
    protected final double height = Constants.NODE_HEIGHT;

    protected Node(String name) {
        this.name = name;
        setStyle("-fx-background-color:white;");
        detailsLabel = new Label(name);
        this.setMinSize(width, height);
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
