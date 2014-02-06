package com.LPSWorkflow.model.visualComponent;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private Label text;
    private HBox goalDefinition;

    public ActionNode(String name, HBox goalDefinition) {
        super(name);
        // TODO remove test
        this.goalDefinition = goalDefinition;

        if(goalDefinition != null){
            goalDefinition.setStyle("-fx-border-color:black; -fx-padding:18px;");
            getChildren().addAll(goalDefinition);
        } else {
            text = new Label(name);
            text.setStyle("-fx-border-color:black; -fx-padding:18px; -fx-font-size:25px");

            getChildren().addAll(text);
        }


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
