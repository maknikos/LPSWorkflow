package com.LPSWorkflow.model.visualComponent;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public class PartialOrderNode extends Node {
    private StackPane stackPane;
    private Label text;
    private Circle circleBorder;

    public PartialOrderNode() {
        super("||");
        stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setMinWidth(width);
        circleBorder = new Circle();
        circleBorder.setRadius(15);
        circleBorder.setFill(Paint.valueOf("Transparent"));
        circleBorder.setStroke(Paint.valueOf("Black"));
        text = new Label("AND");
        text.setStyle("-fx-font-size:12px");

        stackPane.getChildren().addAll(text, circleBorder);
        getChildren().addAll(stackPane);
    }

    @Override
    public String getName() {
        return null;
    }
}
