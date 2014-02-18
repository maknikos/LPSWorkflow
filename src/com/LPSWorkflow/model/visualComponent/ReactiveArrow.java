package com.LPSWorkflow.model.visualComponent;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class ReactiveArrow extends Parent {
    private StackPane stackPane;
    private Line line;
    private Line head1;
    private Line head2;

    public ReactiveArrow() {
        stackPane = new StackPane();

        line = new Line(0,0,0,50);
        line.setStrokeWidth(3);
        line.setStroke(Paint.valueOf("Red"));
        head1 = new Line(0,4,3,0);
        head2 = new Line(0,4,-3,0);
        head1.setStrokeWidth(3);
        head2.setStrokeWidth(3);
        head1.setTranslateX(2);
        head1.setTranslateY(24);
        head2.setTranslateX(-2);
        head2.setTranslateY(24);

        stackPane.getChildren().addAll(line, head1, head2);
        getChildren().addAll(stackPane);
    }

}
