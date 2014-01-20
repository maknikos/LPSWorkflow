package com.LPSWorkflow.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * A macro-action entity that can contain other actions
 */
public class MacroAction extends Entity {
    private HBox hBox;
    private Label text;
    private Button expand;
    private boolean isExpanded;
    private Group childGroup;


    public MacroAction(String name, Group childGroup) {
        this.childGroup = childGroup;
        isExpanded = false;
        hBox = new HBox();
        expand = createExpandButton();
        text = new Label(name);
        text.setLayoutX(4);
        text.setLayoutY(2);
        text.setStyle("-fx-padding:3px;");
        hBox.setStyle("-fx-border-color:Black; -fx-padding:3px;");
        hBox.getChildren().addAll(text, expand);

        getChildren().addAll(hBox);
    }

    public Group getChildGroup(){
        return childGroup;
    }

    public boolean isExpanded(){
        return isExpanded;
    }

    private Button createExpandButton() {
        final Button button = new Button("+");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hBox.getChildren().add(new Label("test"));
                isExpanded = !isExpanded;
                if(isExpanded)
                    button.setText("-");
                else
                    button.setText("+");
            }
        });
        return button;
    }

    @Override
    public String getName() {
        return text.getText();
    }
}
