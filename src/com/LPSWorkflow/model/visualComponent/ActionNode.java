package com.LPSWorkflow.model.visualComponent;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private Group group;
    private Label text;
    private HBox goalDefinition;

    public ActionNode(String name, HBox goalDefinition) {
        super(name);
        // TODO remove test

        if(goalDefinition != null){
            goalDefinition.setStyle("-fx-border-color:black; -fx-padding:18px;");
            this.goalDefinition = goalDefinition;
        } else {
            this.goalDefinition = new HBox();
        }

        this.group = new Group();
        this.group.setStyle("-fx-border-color:black; -fx-padding:18px;");

        this.text = new Label(name);
        this.text.setStyle("-fx-border-color:black; -fx-padding:18px; -fx-font-size:25px");

        group.getChildren().addAll(this.text, this.goalDefinition);
        getChildren().add(group);

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
//        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });
    }

    //TODO clear up
    @Override
    public void resize(double v, double v2) {
        super.resize(v, v2);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
//        if(text != null){
//            text.setText(this.getName() + "\t" + x + ":" + y);
//        }
    }

    @Override
    public void resizeRelocate(double v, double v2, double v3, double v4) {
        super.resizeRelocate(v, v2, v3, v4);
    }

    @Override
    public double getWidth(){
        return this.getLayoutBounds().getWidth();
    }

    @Override
    public double getHeight(){
        return this.getLayoutBounds().getHeight();
    }
}
