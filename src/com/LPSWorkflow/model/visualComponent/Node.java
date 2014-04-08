package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends StackPane {
    private String name;
    protected Label text;
    protected final double width = Constants.NODE_WIDTH;
    protected final double height = Constants.NODE_HEIGHT;

    protected Node(String name) {
        this.name = name;
        this.setMinSize(width, height);
        setStyle("-fx-background-color:white;");
        text = new Label(name);
        text.setAlignment(Pos.CENTER);
        text.setPrefSize(width, height);
        text.setStyle("-fx-font-size:25px");
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
