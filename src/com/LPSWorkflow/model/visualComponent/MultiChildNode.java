package com.LPSWorkflow.model.visualComponent;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public abstract class MultiChildNode extends Node {
    private Circle circleBorder;

    public MultiChildNode(String name) {
        super(name);
        this.setMinSize(width, 0);
        circleBorder = new Circle();
        circleBorder.setRadius(15);
        circleBorder.setFill(Paint.valueOf("Transparent"));
        circleBorder.setStroke(Paint.valueOf("Black"));

        text.setPrefSize(width, 0);
        text.setStyle("-fx-font-size:12px");

        getChildren().addAll(text, circleBorder);
    }

    @Override
    public String getName() {
        return null;
    }

}
