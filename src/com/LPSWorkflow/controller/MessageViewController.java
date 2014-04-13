package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.message.Message;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageShape;
import com.LPSWorkflow.model.message.MessageType;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the message view
 */
public class MessageViewController implements Initializable {
    @FXML private VBox messageBox;
    private MessageData messageData;
    private Label moreButton;
    private StringProperty msgCountStr = new SimpleStringProperty("More (0 messages)");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moreButton = createMoreButton();
        messageData = MessageData.getInstance();

        // if ChangeListener is used, it stops listening when a binding in MessageListViewController
        // is introduced, so using invalidation listener instead.
        messageData.messageListProperty().addListener((Observable observable) -> {
                ObservableList<Message> messages = (ObservableList<Message>) observable;
                // sync with messages stored in MessageData instance
                messageBox.getChildren().clear();
                // only display 2 messages, and group the rest
                int count = 0;
                for(Message m : messages){
                    count++;
                    if(count > 2){
                        messageBox.getChildren().add(moreButton);
                        msgCountStr.setValue("More (" + (messages).size() + " messages)");
                        break;
                    }
                    messageBox.getChildren().add(createMessage(m));
                }
            });
    }

    private Label createMoreButton() {
        Label moreLabel = new Label();
        moreLabel.textProperty().bind(msgCountStr);
        moreLabel.getStyleClass().add("message-more-label");
        moreLabel.setMaxWidth(Double.MAX_VALUE);
        moreLabel.setOnMouseClicked((MouseEvent m) -> {
                try {
                    // launch the message list view in a new window(Stage)
                    Parent root = FXMLLoader.load(getClass().getResource("../view/messageListView.fxml"));
                    Stage messageStage = new Stage();
                    messageStage.setScene(new Scene(root, 600, 150));
                    messageStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        return moreLabel;
    }

    private MessageShape createMessage(Message message) {
        return new MessageShape(message);
    }



    //***** TEST ****** TODO clear up
    private int count = 0;
    public void handleTest(ActionEvent actionEvent) {
        messageData.getMessageList().add(new Message("This is an important message( " + count + " ) and very long as well. it will go over the edge.", MessageType.ERROR));
        count++;
    }
}
