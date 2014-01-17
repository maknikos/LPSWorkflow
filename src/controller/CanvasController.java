package controller;

import LPS.LPSFileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Event;
import model.FileData;
import model.Idle;

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
    public void handleTextChange() {
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
            Event e2 = new Event(goal);
            Idle end = new Idle("End");
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(start, e1, e2, end);
            contentGroup.getChildren().addAll(hbox);

            fileData.setFilePath(rules.get(0).toString());
        }

        //TODO do nothing if no reactive rules exist? give a message?
    }


}
