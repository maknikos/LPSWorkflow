package com.LPSWorkflow.model.message;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * deals with messages
 */
public class MessageData {
    private static MessageData instance = null;

    public final static MessageData getInstance() {
        if (MessageData.instance == null) {
            synchronized (MessageData.class) {
                if (MessageData.instance == null) {
                    MessageData.instance = new MessageData();
                }
            }
        }
        return instance;
    }

    private ListProperty<Message> messageList = new SimpleListProperty<Message>(FXCollections.<Message>observableArrayList());
    public ListProperty<Message> messageListProperty(){
        return messageList;
    }

    public final void setMessageList(ObservableList<Message> list){
        messageList.set(list);
    }

    public final List<Message> getMessageList(){
        return messageList.get();
    }

    public void sendMessage(String content, MessageType type){
        getMessageList().add(new Message(content, type));
    }
}
