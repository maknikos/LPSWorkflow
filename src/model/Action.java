package model;

import javafx.scene.control.Label;

public class Action extends Entity {
    private Label text;

    public Action(String name) {
        text = new Label(name);
        text.setStyle("-fx-border-color:Black; -fx-padding:3px;");
        text.setLayoutX(4);
        text.setLayoutY(2);
        getChildren().addAll(text);
    }
}
