package com.LPSWorkflow.model;

import javafx.scene.control.Label;

/**
 * An event entity used for external events
 */
public class Event extends Entity {
    private Label text;

    public Event(String name) {
        text = new Label(name);
        text.setStyle("-fx-border-color:Black; -fx-padding:3px;");
        text.setLayoutX(4);
        text.setLayoutY(2);
        getChildren().addAll(text);
    }
}
