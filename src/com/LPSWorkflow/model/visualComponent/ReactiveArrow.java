package com.LPSWorkflow.model.visualComponent;

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

        // bind arrow head to the end of the line
        head1.startXProperty().bind(endPoint.xProperty());
        head1.startYProperty().bind(endPoint.yProperty());
        head2.startXProperty().bind(endPoint.xProperty());
        head2.startYProperty().bind(endPoint.yProperty());
        head1.endXProperty().bind(endPoint.xProperty().subtract(5));
        head1.endYProperty().bind(endPoint.yProperty().subtract(5));
        head2.endXProperty().bind(endPoint.xProperty().add(5));
        head2.endYProperty().bind(endPoint.yProperty().subtract(5));

        // bind the endPoint to the endNode
        endPoint.xProperty().bind(endNode.layoutXProperty().add(endNode.widthProperty().divide(2))); //centre
        endPoint.yProperty().bind(endNode.layoutYProperty());

        // bind the startPoint to the startNode
        startPoint.xProperty().bind(startNode.layoutXProperty().add(startNode.widthProperty().divide(2)));
        startPoint.yProperty().bind(startNode.layoutYProperty().add(startNode.heightProperty())); //TODO
    }
}
