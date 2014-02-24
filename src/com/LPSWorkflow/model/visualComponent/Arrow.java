package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.shape.*;

import java.util.Set;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class Arrow extends Parent {
    final Path path;
    final MoveTo startPoint;
    final LineTo line1;
    final LineTo line2;
    final LineTo endPoint;
    final Line head1;
    final Line head2;

    public Arrow(final Node startNode, final Node endNode, final Set<Arrow> arrowsToEndNode) {
        path = new Path();
        path.setStyle("-fx-stroke-width:1;");

        startPoint = new MoveTo(0, 0);
        line1 = new LineTo(0, 0); // intermediate points
        line2 = new LineTo(0, 0); // TODO change shape
        endPoint = new LineTo(0, 0);

        head1 = new Line(0,0,0,0);
        head2 = new Line(0,0,0,0);
        getChildren().addAll(path, head1, head2);

        head1.startXProperty().bind(endPoint.xProperty());
        head1.startYProperty().bind(endPoint.yProperty());
        head2.startXProperty().bind(endPoint.xProperty());
        head2.startYProperty().bind(endPoint.yProperty());

        if(startNode == null){
            // if there is no start node, draw a straight line from endNode
            path.getElements().addAll(startPoint, endPoint);
            startPoint.xProperty().bind(endPoint.xProperty());
        } else {
            path.getElements().addAll(startPoint,line1,line2,endPoint);
            line1.yProperty().bind(line2.yProperty());
            line1.xProperty().bind(startPoint.xProperty());
            line2.xProperty().bind(endPoint.xProperty());

            //TODO change arrow shape
            startNode.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
                @Override
                public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
                    double startX = newBounds.getMinX() + (0.5 * newBounds.getWidth()); // centre
                    double startY = newBounds.getMinY()  + newBounds.getHeight();
                    startPoint.setX(startX);
                    startPoint.setY(startY);
                    // start position can change when the parent node expands/collapses
                    // always keep the distance (prevents the arrow facing upwards).
                    // use the minimum height between the arrows pointing to the same endNode;
                    double maxStartY = 0.0;
                    for(Arrow a : arrowsToEndNode){
                        maxStartY = Math.max(a.startPoint.getY(), maxStartY);
                    }
                    endNode.setLayoutY(maxStartY + Constants.NODE_VERTICAL_GAP);
                }
            });

        }

        endNode.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
                double endX = newBounds.getMinX() + (0.5 * newBounds.getWidth()); // centre
                double endY = newBounds.getMinY();
                endPoint.setX(endX);
                endPoint.setY(endY);
                if(startNode == null){
                    startPoint.setY(endY - Constants.NODE_VERTICAL_GAP);
                } else {
                    line2.setY(endY - Constants.NODE_VERTICAL_GAP/2);
                }

                head1.setEndX(endX-5);
                head1.setEndY(endY-5);
                head2.setEndX(endX+5);
                head2.setEndY(endY-5);
            }
        });
    }
}
