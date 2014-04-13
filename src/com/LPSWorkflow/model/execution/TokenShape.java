package com.LPSWorkflow.model.execution;

import javafx.scene.shape.Circle;

/**
 * Visual component of an execution agent
 */
public class TokenShape extends Circle {
    public TokenShape() {
        super(10);
        this.getStyleClass().add("token");
    }
}
