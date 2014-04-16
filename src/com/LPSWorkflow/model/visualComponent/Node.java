package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.common.EntityType;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * Visual component that represents an element in LPS program.
 */
public abstract class Node extends StackPane {
    private String name;
    protected Label text;
    protected final double width = Constants.NODE_WIDTH;
    protected final double height = Constants.NODE_HEIGHT;

    protected Node(String name) {
        this.name = name;
        this.setMinSize(width, height);
        text = new Label(name);
        text.setPrefSize(width, height);
        this.getStyleClass().add("lps-node");
        text.getStyleClass().add("lps-node-text");

        installTooltip();
    }

    private void installTooltip() {
        //TODO need to improve.. relying on 'OnExit' can sometimes leave a tooltip floating around
        //TODO Also, for expanded goal Definition, may need to decide which tooltip to show (containing node, or the one inside)
        final Tooltip t = new Tooltip(name);
        this.setOnMouseEntered((MouseEvent e) -> {
            if(getScene().getWindow().isFocused()){
                Bounds bounds = localToScreen(this.getLayoutBounds());
                t.show(this, bounds.getMaxX(), bounds.getMinY());
            }
        });
        this.setOnMouseExited(e -> {
            if(getScene().getWindow().isFocused()){
                t.hide();
            }
        });
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract EntityType getType();
}
