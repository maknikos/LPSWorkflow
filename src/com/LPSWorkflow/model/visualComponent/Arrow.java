package com.LPSWorkflow.model.visualComponent;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class Arrow extends Parent {
    private StackPane stackPane;
    private Line line;
    private Line head1;
    private Line head2;
    private double angle;

    public Arrow(double angle) {
        stackPane = new StackPane();
        this.angle = angle;



        if(((int) angle) == 0){
            line = new Line(0,0,50,0);
            line.setStrokeWidth(3);

            head1 = new Line(4,0,0,3);
            head2 = new Line(4,0,0,-3);
            head1.setStrokeWidth(3);
            head1.setTranslateX(24);
            head1.setTranslateY(2);
            head2.setStrokeWidth(3);
            head2.setTranslateX(24);
            head2.setTranslateY(-2);

            stackPane.getChildren().addAll(line, head1, head2);
        } else {
            double endX = Math.cos(Math.toRadians(-angle)) * 100.0;
            double endY = Math.sin(Math.toRadians(-angle)) * 100.0;
            line = new Line(0,0,endX,endY);
            line.setStrokeWidth(3);

            stackPane.getChildren().addAll(line);
        }

        //stackPane.setRotate(angle);

        getChildren().addAll(stackPane);
    }
}
