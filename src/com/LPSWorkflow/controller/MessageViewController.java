package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.message.Message;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageShape;
import com.LPSWorkflow.model.message.MessageType;
import javafx.beans.Observable;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageData = MessageData.getInstance();
        moreButton = createMoreButton();

        // if ChangeListener is used, it stops listening when a binding in MessageListViewController
        // is introduced, so using invalidation listener instead.
        messageData.messageListProperty().addListener((Observable observable) -> {
            ObservableList<Message> messages = (ObservableList<Message>) observable;
            // sync with messages stored in MessageData instance
            messageBox.getChildren().clear();
            // only display 2 messages, and group the rest
            int count = messages.size();

            for (int i = 0; i < Math.min(count, 2); i++) {
                messageBox.getChildren().add(createMessage(messages.get(i)));
            }
            if(count > 2){
                messageBox.getChildren().add(moreButton);
            }
        });
    }

    private Label createMoreButton() {
        Label moreLabel = new Label();
        SimpleStringProperty prefix = new SimpleStringProperty("More (");
        SimpleStringProperty suffix = new SimpleStringProperty(" message)");
        StringExpression messageCountStr = prefix.concat(messageData.messageListProperty().sizeProperty()).concat(suffix);
        moreLabel.textProperty().bind(messageCountStr);
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
        messageData.getMessageList().add(new Message("This is an important message( " + count + " ) and very long as well. it will go over the edge." +
                "I mean it. Really. ", MessageType.ERROR));
        count++;
    }
}
