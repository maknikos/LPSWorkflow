package com.LPSWorkflow.model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

/**
 * A macro-action entity that can contain other actions
 */
public class Fluent extends Entity {
    private TitledPane expander;
    private Label text;
    private Group childGroup;
    private boolean isMacro;

    public Fluent(String name, Group childGroup) {
        this.childGroup = childGroup;
        isMacro = (childGroup != null && childGroup.getChildren().size() > 0);

        //TODO private fields (text and expander) may be null depending on isMacro .. make it unified?

        // If not a macro-action, no need to have an expand button
        if(isMacro)
        {
            expander = new TitledPane(name, childGroup);
            expander.setExpanded(false);
            this.getChildren().add(expander);
        }
        else
        {
            text = new Label(name);
            text.setLayoutX(4);
            text.setLayoutY(2);
            text.setStyle("-fx-border-color:Black; -fx-padding:3px;");
            text.setStyle("-fx-padding:3px;");
            this.getChildren().add(text);
        }
    }

    public Group getChildGroup(){
        return childGroup;
    }

    public boolean isMacro() {
        return isMacro;
    }

    //TODO need a way to set childGroup, and make it a macro

    @Override
    public String getName() {
        return text.getText();
    }
}
