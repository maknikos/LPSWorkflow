package com.LPSWorkflow.model.message;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Visual component for Messages
 */
public class MessageShape extends Region {
    private MessageData messageData;
    private Message message;

    private StackPane containerPane;
    private Label contentLabel;
    private Button closeButton;

    public MessageShape(Message msg) { //TODO different colours for different types of message
        messageData = MessageData.getInstance();
        message = msg;

        containerPane = new StackPane();
        contentLabel = new Label(msg.getContent());
        contentLabel.setStyle("-fx-border-color:#FF9999BB; -fx-border-radius:5px; " +
                "-fx-background-color:#FF999999; -fx-background-radius:5px;" +
                "-fx-text-fill:#771111FF; -fx-font-size:12px;" +
                "-fx-padding:3 15 3 10");
        closeButton = new Button("x");
        containerPane.getChildren().addAll(contentLabel, closeButton);
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(contentLabel, new Insets(3, 6, 3, 0));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // remove itself from the MessageData when closed
                messageData.getMessageList().remove(message);
            }
        });

        this.getChildren().add(containerPane);
    }
}
