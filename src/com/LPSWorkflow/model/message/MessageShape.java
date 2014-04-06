package com.LPSWorkflow.model.message;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Visual component for Messages
 */
public class MessageShape extends Region {
    private StackPane containerPane;
    private Label contentLabel;
    private Button closeButton;

    public MessageShape(Message msg) { //TODO different colours for different types of message
        containerPane = new StackPane();
        contentLabel = new Label(msg.getContent());
        contentLabel.setStyle("-fx-border-color:#FF9999BB; -fx-border-radius:5px; " +
                "-fx-background-color:#FF999999; -fx-background-radius:5px;" +
                "-fx-text-fill:#882222FF;" +
                "-fx-padding:10px");
        closeButton = new Button("x");
        containerPane.getChildren().addAll(contentLabel, closeButton);
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(contentLabel, new Insets(6, 6, 6, 6));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // remove itself from the parent when closed
                Pane parent = (Pane) getParent();
                parent.getChildren().remove(containerPane.getParent());
            }
        });

        this.getChildren().add(containerPane);
    }
}
