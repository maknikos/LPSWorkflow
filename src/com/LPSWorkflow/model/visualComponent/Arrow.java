package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.common.EntityType;
import javafx.css.PseudoClass;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Set;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class Arrow extends Parent {
    private final Path path;
    private final MoveTo startPoint;
    private final LineTo line1;
    private final LineTo line2;
    private final LineTo endPoint;
    private final Line head1;
    private final Line head2;
    private Label label;

    public Arrow(final Node startNode, final Node endNode, final Set<Arrow> arrowsToEndNode, boolean arrowForTrue) {
        path = new Path();
        head1 = new Line(0,0,0,0);
        head2 = new Line(0,0,0,0);
        startPoint = new MoveTo(0, 0);
        line1 = new LineTo(0, 0); // intermediate points
        line2 = new LineTo(0, 0);
        endPoint = new LineTo(0, 0);
        label = new Label("T");
        label.getStyleClass().add("arrow-label");
        path.getStyleClass().add("arrow");
        head1.getStyleClass().add("arrow");
        head2.getStyleClass().add("arrow");
        getChildren().addAll(path, head1, head2);

        if (!arrowForTrue) {
            path.pseudoClassStateChanged(PseudoClass.getPseudoClass("false"), true);
            head1.pseudoClassStateChanged(PseudoClass.getPseudoClass("false"), true);
            head2.pseudoClassStateChanged(PseudoClass.getPseudoClass("false"), true);
            label.setText("F");
        }

        head1.startXProperty().bind(endPoint.xProperty());
        head1.startYProperty().bind(endPoint.yProperty());
        head2.startXProperty().bind(endPoint.xProperty());
        head2.startYProperty().bind(endPoint.yProperty());

        if(startNode == null){
            // if there is no start node, draw a straight line from endNode
            path.getElements().addAll(startPoint, endPoint);
            startPoint.xProperty().bind(endPoint.xProperty());
        } else {
            // T and F labels for Fluents
            if(startNode.getType() == EntityType.FLUENT){
                label.layoutXProperty().bind(line1.xProperty().add(line2.xProperty()).divide(2).subtract(7));
                label.layoutYProperty().bind(line1.yProperty().subtract(9));
                getChildren().add(label);
            }

            path.getElements().addAll(startPoint,line1,line2,endPoint);
            line1.yProperty().bind(line2.yProperty());
            line1.xProperty().bind(startPoint.xProperty());
            line2.xProperty().bind(endPoint.xProperty());

            startNode.boundsInParentProperty().addListener((observableValue, oldBounds, newBounds) -> {
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
            });

        }

        endNode.boundsInParentProperty().addListener((observableValue, oldBounds, newBounds) -> {
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
        });
    }
}
