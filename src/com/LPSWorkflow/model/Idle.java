package com.LPSWorkflow.model;

import javafx.scene.control.Label;

/**
 * Idle state, indicating the starting point of the workflow
 */
public class Idle extends Entity {
    private Label text;

    public Idle(String name) {
        text = new Label(name);
        text.setStyle("-fx-border-color:Green; -fx-padding:3px;");
        text.setLayoutX(4);
        text.setLayoutY(2);
        getChildren().addAll(text);
    }
}
