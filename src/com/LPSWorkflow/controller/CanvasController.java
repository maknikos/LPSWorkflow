package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.model.Event;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.Idle;
import com.LPSWorkflow.model.MacroAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    private FileData fileData;
    private LPSFileManager fileManager;


    @FXML
    private Group contentGroup;

    @FXML
    private Label filePathLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        filePathLabel.textProperty().bind(fileData.filePathProperty());
        fileManager = new LPSFileManager();
    }

    //TODO change the name
    public void handleDrawAction() {
        fileManager.openFile(fileData.getFilePath());
        ArrayList rules = fileManager.getReactiveRules();

        if(rules == null){
            return; //TODO handle fail case
        }

        drawReactiveRules(rules);
    }

    private void drawReactiveRules(ArrayList rules) {
        //TODO currently only using the first element

        if(rules.size() > 0){
            String cause = fileManager.getCause(rules.get(0));
            String goal = fileManager.getGoal(rules.get(0));

            //TODO null check

            //TODO make connections
            Idle start = new Idle("Start");
            Event e1 = new Event(cause);
            MacroAction a1 = new MacroAction(goal);
            Idle end = new Idle("End");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(start, drawArrow(), e1, drawArrow(), a1, drawArrow(), end);
            contentGroup.getChildren().addAll(vBox);
        }

        //TODO do nothing if no reactive rules exist? give a message?
    }

    private Polygon drawArrow(){
        Polygon arrow = new Polygon(
                7.5, 0,
                15, 15,
                7.51, 15,
                7.51, 40,
                7.49, 40,
                7.49, 15,
                0, 15);
        arrow.setFill(Color.WHITE);
        arrow.setStroke(Color.BLACK);
        arrow.setRotate(180); //TODO make the arrow in reverse direction in the first place
        return arrow;
    }

}
