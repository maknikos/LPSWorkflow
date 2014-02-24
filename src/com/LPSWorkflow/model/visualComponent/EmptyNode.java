package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import javafx.scene.layout.VBox;

/**
 * Node with width of NODE_WIDTH and height 0.
 */
public class EmptyNode extends Node {

    public EmptyNode() {
        super("EMPTY");

        VBox vBox = new VBox();
        vBox.setPrefWidth(Constants.NODE_WIDTH);
        getChildren().add(vBox);
    }
}
