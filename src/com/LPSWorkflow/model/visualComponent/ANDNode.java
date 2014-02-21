package com.LPSWorkflow.model.visualComponent;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public class AndNode extends Node {
    private StackPane stackPane;
    private Label text;
    private Circle circleBorder;

    public AndNode() {
        super("AND");
        stackPane = new StackPane();
        circleBorder = new Circle();
        circleBorder.setRadius(30);
        circleBorder.setFill(Paint.valueOf("Transparent"));
        circleBorder.setStroke(Paint.valueOf("Black"));
        text = new Label("AND");
        text.setStyle("-fx-padding:15px; -fx-font-size:25px");

        stackPane.getChildren().addAll(text, circleBorder);
        getChildren().addAll(stackPane);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}
