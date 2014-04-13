package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;
import javafx.scene.shape.Polygon;

/**
 * Diamond shaped fluent node
 */
public class FluentNode extends Node {
    public FluentNode(String name) {
        super(name);

        Polygon diamond = new Polygon(0,height/2,width/2,height,width,height/2,width/2,0);
        diamond.getStyleClass().add("fluent-diamond");
        getChildren().addAll(diamond, text);
    }

    @Override
    public EntityType getType() {
        return EntityType.FLUENT;
    }
}
