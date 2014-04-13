package com.LPSWorkflow.model.execution;

import javafx.scene.shape.Circle;

/**
 * Visual component of an execution agent
 */
public class ExecCircle extends Circle {
    public ExecCircle() {
        super(10);
        this.getStyleClass().add("token");
    }
}
