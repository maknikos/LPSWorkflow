package com.LPSWorkflow.model.visualComponent;

import javafx.scene.shape.Circle;

/**
 * Circular OR node that has multiple arrows going out from it
 */
public abstract class MultiChildNode extends Node {
    private Circle circleBorder;

    public MultiChildNode(String name) {
        super(name);
        this.setMinSize(width, 0);
        circleBorder = new Circle(15);
        circleBorder.getStyleClass().add("multiChildNode-border");
        text.setPrefSize(width, 0);
        text.getStyleClass().add("multiChildNode");
        getChildren().addAll(circleBorder, text);
    }

    @Override
    public String getName() {
        return null;
    }

}
