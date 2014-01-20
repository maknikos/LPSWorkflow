package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.GoalManager;
import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.LPS.ReactiveRuleManager;
import com.LPSWorkflow.model.Event;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.Fluent;
import com.LPSWorkflow.model.Idle;
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
    private ReactiveRuleManager reactiveRuleManager;
    private GoalManager goalManager;


    @FXML
    private Group contentGroup;

    @FXML
    private Label filePathLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        filePathLabel.textProperty().bind(fileData.filePathProperty());

        //TODO Should I get the singleton values at the constructor? or every time I use a getter?
        fileManager = new LPSFileManager();
        reactiveRuleManager = new ReactiveRuleManager();
        goalManager = new GoalManager();
    }

    //TODO change the name
    public void handleDrawAction() {
        fileManager.openFile(fileData.getFilePath());
        contentGroup.getChildren().clear();
        drawReactiveRules();
    }

    private void drawReactiveRules() {

        //TODO do nothing if no reactive rules exist? give a message?
        if(reactiveRuleManager.size() > 0){
            //TODO currently only using the first element
            String cause = reactiveRuleManager.getReactiveRuleCause(0);
            String goal = reactiveRuleManager.getReactiveRuleGoal(0);

            //TODO null check

            //TODO make connections
            Idle start = new Idle("Start");
            Event e1 = new Event(cause);
            Fluent a1 = new Fluent(goal, drawGoalGroup(goal));
            Idle end = new Idle("End");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(start, drawArrow(), e1, drawArrow(), a1, drawArrow(), end);
            contentGroup.getChildren().addAll(vBox);
        }

    }

    private Group drawGoalGroup(String goal) {
        Group group = new Group();
        ArrayList goalDefinitions = goalManager.getGoalDefinitions(goal);

        return group;

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
