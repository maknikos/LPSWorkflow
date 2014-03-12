package com.LPSWorkflow.model.execution;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Visual component of an execution agent
 */
public class ExecCircle extends Circle {
    public ExecCircle() {
        super(10);
        this.setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
        this.setStroke(Color.GOLD);
        this.setStrokeWidth(2);
        this.setStrokeType(StrokeType.OUTSIDE);
    }
}
