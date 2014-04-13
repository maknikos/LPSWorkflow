package com.LPSWorkflow.model.message;

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
        contentLabel.setMaxWidth(500);
        closeButton = new Button("x");
        contentLabel.getStyleClass().add("message-content-label");
        closeButton.getStyleClass().add("message-close-button");

        containerPane.getChildren().addAll(contentLabel, closeButton);
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(contentLabel, new Insets(3, 10, 3, 0));

        //TODO when clicked, show details of the particular message

        closeButton.setOnAction(actionEvent -> {
            // remove itself from the MessageData when closed
            messageData.getMessageList().remove(message);
        });

        this.getChildren().add(containerPane);
    }
}
