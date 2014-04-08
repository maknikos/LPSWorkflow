package com.LPSWorkflow.model.visualComponent;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Visual component representing an action
 */
public class ExitNode extends Node {
    private VBox vBox;

    public ExitNode(String name) {
        super(name);

        //TODO cleanup the structure of the ActionNode
        vBox = new VBox();
        vBox.setStyle("-fx-border-color:black; -fx-border-radius:5px");
        vBox.setAlignment(Pos.CENTER);

        text.setMinWidth(width);
        text.setMaxWidth(Double.MAX_VALUE);

        vBox.getChildren().add(text);
        getChildren().addAll(vBox);
    }
}
