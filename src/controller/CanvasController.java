package controller;

import LPS.LPSFileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import model.FileData;

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

    public void handleTextChange() {
        fileManager.openFile(fileData.getFilePath());
        ArrayList rules = fileManager.getReactiveRules();

        if(rules == null){
            return; //TODO handle fail case
        }

        drawReactiveRules(rules);
    }

    private void drawReactiveRules(ArrayList rules) {
        fileData.setFilePath(rules.get(0).toString());
    }


}
