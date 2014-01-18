package com.LPSWorkflow.model;

import javafx.scene.control.Label;

/**
 * A macro-action entity that can contain other actions
 */
public class MacroAction extends Entity {
    private Label text;

    public MacroAction(String name) {
        text = new Label(name);
        text.setStyle("-fx-border-color:Black; -fx-padding:3px;");
        text.setLayoutX(4);
        text.setLayoutY(2);
        getChildren().addAll(text);
    }
}
