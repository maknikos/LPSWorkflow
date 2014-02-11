package com.LPSWorkflow.model.visualComponent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
            goalDefinition.setStyle("-fx-border-color:black; -fx-padding:18px;");
            this.goalDefinition = goalDefinition;
        } else {
            this.goalDefinition = new VBox();
        }

        this.group = new Group();

        this.expandButton = new Button("+");
        this.expandButton.setVisible(hasGoalDefinition());
        this.text = new Label(name);
        this.text.setStyle("-fx-border-color:black; -fx-font-size:25px");
        this.text.setPrefSize(width, height);
        this.text.setAlignment(Pos.CENTER);

        group.getChildren().addAll(this.text, this.goalDefinition);
        getChildren().addAll(group, this.expandButton);

        setExpanded(false);

        this.expandButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ActionNode sourceNode = (ActionNode) ((Button) mouseEvent.getSource()).getParent();
                sourceNode.setExpanded(!sourceNode.isExpanded());
            }
        });
//        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println(
//                        String.format("%s is being dragged to (%f:%f) which is (%f:%f)",
//                                getName(), mouseEvent.getSceneX(), mouseEvent.getSceneY(),
//                                mouseEvent.getX(), mouseEvent.getY()
//                        ));
//            }
//        });
//
    }

    private void setExpanded(boolean b) {
        this.isExpanded = b;
        this.goalDefinition.setVisible(b);
    }

    private boolean isExpanded() {
        return isExpanded;
    }

    public boolean hasGoalDefinition(){
        return goalDefinition.getChildren().size() > 0;
    }
}
