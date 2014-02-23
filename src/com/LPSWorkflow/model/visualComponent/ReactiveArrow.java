package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

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

        path.setStyle("-fx-stroke-width:3px; -fx-stroke-dash-array:8 6;");

        startPoint = new MoveTo(0, 0);
        endPoint = new LineTo(0, 0);

        head1 = new Line(0,0,0,0);
        head2 = new Line(0,0,0,0);
        head1.setStyle("-fx-stroke-width:3px;");
        head2.setStyle("-fx-stroke-width:3px;");
        getChildren().addAll(path, head1, head2);

        path.getElements().addAll(startPoint, endPoint);

        //TODO change arrow shape
        startNode.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
                double startX = newBounds.getMinX() + (0.5 * newBounds.getWidth()); // centre
                double startY = newBounds.getMinY()  + newBounds.getHeight();
                startPoint.setX(startX);
                startPoint.setY(startY);
                // start position can change when the parent node expands/collapses
                // always keep the distance (prevents the arrow facing upwards)
                endNode.setLayoutY(startPoint.getY() + Constants.NODE_VERTICAL_GAP);
            }
        });

        endNode.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
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
            }
        });
    }
}
