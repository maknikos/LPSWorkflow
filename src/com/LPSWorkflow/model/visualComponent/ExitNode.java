package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;

/**
 * Visual component representing an action
 */
public class ExitNode extends Node {
    public ExitNode() {
        super("Exit");
        text.setText("");
        text.setPrefSize(25, 25);
        text.getStyleClass().add("exit-node-text");
        this.setMinHeight(0);
        getChildren().addAll(text);
    }

    @Override
    public EntityType getType() {
        return EntityType.EXIT;
    }
}
