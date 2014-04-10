package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;
import javafx.scene.layout.VBox;

/**
 * Visual component representing an action
 */
public class ExitNode extends Node {
    private VBox vBox;

    public ExitNode() {
        super("Exit");

        //TODO cleanup the structure of the ActionNode
        text.setText("");
        text.setPrefSize(25, 25);
        text.setStyle("-fx-font-size:12px; -fx-background-color:#33993355; -fx-background-radius:6px; " +
                "-fx-border-color:#339933FF; -fx-border-radius:6px; -fx-border-width:4px");

        this.setMinHeight(0);
        getChildren().addAll(text);
    }

    @Override
    public EntityType getType() {
        return EntityType.EXIT;
    }
}
