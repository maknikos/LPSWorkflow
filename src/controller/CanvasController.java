package controller;

import LPS.LPSFileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.FileData;
import model.ReactiveRule;

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
    private VBox contentBox;

    @FXML
    private Label filePathLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        filePathLabel.textProperty().bind(fileData.filePathProperty());
        fileManager = new LPSFileManager();
    }

    public void handleTextChange() {
        ArrayList rules = fileManager.openFile(fileData.getFilePath());

        if(rules == null){
            return; //TODO handle fail case
        }

        drawReactiveRules(rules);
    }

    private void drawReactiveRules(ArrayList<ReactiveRule> rules) {
        fileData.setFilePath(rules.get(0).toString());
    }


}
