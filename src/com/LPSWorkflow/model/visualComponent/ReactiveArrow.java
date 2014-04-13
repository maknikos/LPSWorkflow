package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class ReactiveArrow extends Parent {
    final Path path;
    final MoveTo startPoint;
    final LineTo endPoint;
    final Line head1;
    final Line head2;

    public ReactiveArrow(final Node startNode, final Node endNode) {
        path = new Path();
        path.getStyleClass().add("reactive-arrow");

        startPoint = new MoveTo(0, 0);
        endPoint = new LineTo(0, 0);
        head1 = new Line(0,0,0,0);
        head2 = new Line(0,0,0,0);
        head1.getStyleClass().add("reactive-arrow");
        head2.getStyleClass().add("reactive-arrow");
        getChildren().addAll(path, head1, head2);

        path.getElements().addAll(startPoint, endPoint);

        //TODO change arrow shape
        startNode.boundsInParentProperty().addListener((observableValue, oldBounds, newBounds) -> {
            double startX = newBounds.getMinX() + (0.5 * newBounds.getWidth()); // centre
            double startY = newBounds.getMinY()  + newBounds.getHeight();
            startPoint.setX(startX);
            startPoint.setY(startY);
            // start position can change when the parent node expands/collapses
            // always keep the distance (prevents the arrow facing upwards)
            endNode.setLayoutY(startPoint.getY() + Constants.NODE_VERTICAL_GAP);
        });

        endNode.boundsInParentProperty().addListener((observableValue, oldBounds, newBounds) -> {
            double endX = newBounds.getMinX() + (0.5 * newBounds.getWidth()); // centre
            double endY = newBounds.getMinY();
            endPoint.setX(endX);
            endPoint.setY(endY);

            head1.setStartX(endX);
            head1.setStartY(endY);
            head1.setEndX(endX-5);
            head1.setEndY(endY-5);
            head2.setStartX(endX);
            head2.setStartY(endY);
            head2.setEndX(endX+5);
            head2.setEndY(endY-5);
        });
    }
}
