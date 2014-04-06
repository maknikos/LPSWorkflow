package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.message.Message;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageShape;
import com.LPSWorkflow.model.message.MessageType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the message view
 */
public class MessageViewController implements Initializable {
    @FXML
    private VBox messageBox;

    private MessageData messageData;
    private Label moreButton;
    private StringProperty msgCountStr = new SimpleStringProperty("More (0 messages)");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moreButton = createMoreButton();
        messageData = MessageData.getInstance();
        messageData.messageListProperty().addListener(new ChangeListener<ObservableList<Message>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Message>> observableValue, ObservableList<Message> oldMessages, ObservableList<Message> messages) {
                // sync with messages stored in MessageData instance
                messageBox.getChildren().clear();

                // only display 2 messages, and group the rest
                int count = 0;
                for(Message m : messages){
                    count++;

                    if(count > 2){
                        // TODO make a group/list of messages
                        messageBox.getChildren().add(moreButton);
                        msgCountStr.setValue("More (" + messages.size() + " messages)");
                        break;
                    }
                    messageBox.getChildren().add(createMessage(m));
                }
            }
        });
    }

    private Label createMoreButton() {
        Label moreLabel = new Label();
        moreLabel.textProperty().bind(msgCountStr);
        moreLabel.setStyle("-fx-border-color:#FF9999BB; -fx-border-radius:5px; " +
                "-fx-background-color:#FF999999; -fx-background-radius:5px;" +
                "-fx-text-fill:#771111FF; -fx-font-size:11px;" +
                "-fx-padding:2 10 2 10");
        return moreLabel;
    }

    private MessageShape createMessage(Message message) {
        return new MessageShape(message);
    }

    //***** TEST ****** TODO clear up
    private int count = 0;
    public void handleTest(ActionEvent actionEvent) {
        messageData.getMessageList().add(new Message("This is an important message: Message list " + count, MessageType.ERROR));
        count++;
    }
}
