package com.LPSWorkflow.model.visualComponent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private Group group;
    private Label text;
    private Button expandButton;

    private VBox goalDefinition;
    private boolean isExpanded;

    public ActionNode(String name, VBox goalDefinition) {
        super(name);

        if(goalDefinition != null){
            goalDefinition.setStyle("-fx-border-color:black;");
            this.goalDefinition = goalDefinition;
        } else {
            this.goalDefinition = new VBox();
        }

        group = new Group();

        expandButton = new Button("+");
        expandButton.setVisible(hasGoalDefinition());
        text = new Label(name);
        text.setStyle("-fx-border-color:black; -fx-font-size:25px");
        text.setPrefSize(width, height);
        text.setAlignment(Pos.CENTER);

        group.getChildren().addAll(text, this.goalDefinition);
        getChildren().addAll(group, expandButton);

        setExpanded(false);

        // When clicked on expand button, it will show goal definitions ("expand")
        expandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ActionNode sourceNode = (ActionNode) ((Button) actionEvent.getSource()).getParent();
                sourceNode.setExpanded(!sourceNode.isExpanded());
            }
        });
    }

    private void setExpanded(boolean b) {
        isExpanded = b;
        goalDefinition.setVisible(b);

        //TODO there may be a better way to show the label
        if(b){
            text.setStyle("-fx-border-color:transparent; -fx-font-size:25px");
        } else {
            text.setStyle("-fx-border-color:black; -fx-font-size:25px");
        }
    }

    private boolean isExpanded() {
        return isExpanded;
    }

    public boolean hasGoalDefinition(){
        return goalDefinition != null
                && goalDefinition.getChildren().size() > 0;
    }
}
