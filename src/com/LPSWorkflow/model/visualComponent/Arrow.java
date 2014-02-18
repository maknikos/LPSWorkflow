package com.LPSWorkflow.model.visualComponent;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * Draw an arrow consisting of three lines (line and two heads)
 */
public class Arrow extends Parent {
    /**
     * The coordinates for centre of the Node where input arrow points to
     */
    private DoubleProperty inputPointX = new SimpleDoubleProperty();
    private DoubleProperty inputPointY = new SimpleDoubleProperty();

    public DoubleProperty inputPointXProperty(){
        return inputPointX;
    }

    public final double getInputPointX() {
        return inputPointX.get();
    }

    public final void setInputPointX(double x) {
        this.inputPointX.set(x);
    }

    public DoubleProperty inputPointYProperty(){
        return inputPointY;
    }

    public final double getInputPointY() {
        return inputPointY.get();
    }

    public final void setInputPointY(double y) {
        this.inputPointY.set(y);
    }

    private StackPane stackPane;
    private Line line;
    private Line head1;
    private Line head2;

    public Arrow() {
        stackPane = new StackPane();

        line = new Line(0,0,0,50);
        line.setStrokeWidth(3);

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

    public Arrow(Node startNode, VBox endNode) {
        stackPane = new StackPane();
        //TODO merge path and line
        Path path = new Path();
        path.setStrokeWidth(3);

        MoveTo startPoint = new MoveTo();
        startPoint.setAbsolute(true);
        startPoint.xProperty().bind(startNode.layoutXProperty());
        startPoint.yProperty().bind(startNode.layoutYProperty());
        LineTo endPoint = new LineTo();
        endPoint.setAbsolute(true);
        endPoint.xProperty().bind(endNode.layoutXProperty());
        endPoint.yProperty().bind(endNode.layoutYProperty());
//        MoveTo startPoint = new MoveTo(0,0);
//        LineTo endPoint = new LineTo(-50, 50);

        //endPoint.xProperty().bind();



        path.getElements().addAll(startPoint, endPoint);

       // path.proper


//            double endX = Math.cos(Math.toRadians(-angle)) * 100.0;
//            double endY = Math.sin(Math.toRadians(-angle)) * 100.0;
//            line = new Line(0,0,endX,endY);
//            line.setStrokeWidth(3);

        stackPane.getChildren().addAll(path);

        getChildren().addAll(stackPane);
    }
}
