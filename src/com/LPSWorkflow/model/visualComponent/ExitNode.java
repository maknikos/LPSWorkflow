package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;

/**
 * Visual component representing an action
 */
public class ExitNode extends Node {
    public ExitNode() {
        super("Exit");
        text.setText("");
        this.getStyleClass().add("exit-node");
        text.getStyleClass().add("exit-node-text");
        getChildren().addAll(text);
    }

    @Override
    public EntityType getType() {
        return EntityType.EXIT;
    }
}
