package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.message.Message;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageShape;
import com.LPSWorkflow.model.message.MessageType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageData = MessageData.getInstance();
        messageData.messageListProperty().addListener(new ChangeListener<ObservableList<Message>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Message>> observableValue, ObservableList<Message> messages, ObservableList<Message> messages2) {
                //TODO display two messages max. and show an option to display all
                messageBox.getChildren().add(createMessage("ERROR: the file format '.pl' is not supported."));
            }
        });
    }

    private Group createMessage(String message) {
        return new MessageShape(new Message(message, MessageType.ERROR));
    }

    public void handleTest(ActionEvent actionEvent) {
        messageData.getMessageList().add(new Message("Message list", MessageType.ERROR));
    }
}
