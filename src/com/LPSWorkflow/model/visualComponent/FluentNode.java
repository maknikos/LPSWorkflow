package com.LPSWorkflow.model.visualComponent;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

/**
 * Diamond shaped fluent node
 */
public class FluentNode extends Node {
    private Group group;
    private Label text;

    public FluentNode(String name) {
        super(name);

        this.group = new Group();

        this.text = new Label(name);
        this.text.setStyle("-fx-font-size:25px");
        this.text.setPrefSize(width, height);
        this.text.setAlignment(Pos.CENTER);

        Polygon diamond = new Polygon(0,height/2,width/2,height,width,height/2,width/2,0);
        diamond.setStyle("-fx-fill:transparent; -fx-stroke:black;");

        group.getChildren().addAll(this.text, diamond);
        getChildren().add(group);
    }


}
