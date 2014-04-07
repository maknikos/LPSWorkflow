package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.message.MessageData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for MessageListView (contains all messages)
 */
public class MessageListViewController implements Initializable {
    @FXML
    private TableView messageTable;
    private MessageData messageData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageData = MessageData.getInstance();
        messageTable.itemsProperty().bind(messageData.messageListProperty());
    }
}
