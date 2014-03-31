package com.LPSWorkflow.model.message;

import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * Visual component for Messages
 */
public class MessageShape extends Group {
    private Label contentLabel;

    public MessageShape(Message msg) {
        contentLabel = new Label(msg.getContent());
        contentLabel.setStyle("-fx-border-color:#FF9999BB; -fx-border-radius:5px; " +
                "-fx-background-color:#FF999999; -fx-background-radius:5px;" +
                "-fx-text-fill:#882222FF;" +
                "-fx-padding:10px; -fx-margin:10px");
        this.getChildren().add(contentLabel);
    }
}
