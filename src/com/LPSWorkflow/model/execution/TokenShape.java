package com.LPSWorkflow.model.execution;

import com.LPSWorkflow.model.visualComponent.Node;
import javafx.scene.shape.Circle;

/**
 * Visual component of an execution agent
 */
public class TokenShape extends Circle {
    public TokenShape(Node node) {
        super(10);
        setCurrentNode(node);
        this.getStyleClass().add("token");
    }

    public void setCurrentNode(Node node) {
        double minX = node.getParent().getBoundsInParent().getMinX() - node.getParent().getLayoutBounds().getMinX();
        this.setCenterX(minX + node.getLayoutX() + node.getWidth()/2);
        this.setCenterY(node.getBoundsInParent().getMinY());
    }
}
