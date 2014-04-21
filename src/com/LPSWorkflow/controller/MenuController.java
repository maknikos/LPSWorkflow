package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.FileData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the top menu bar
 */
public class MenuController implements Initializable {
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private HBox titleBar;
    private FileData fileData;
    private double initialX;
    private double initialY;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();

        // add handlers so that the window can be moved
        titleBar.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });

        titleBar.setOnMouseDragged((MouseEvent me) -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                titleBar.getScene().getWindow().setX(me.getScreenX() - initialX);
                titleBar.getScene().getWindow().setY(me.getScreenY() - initialY);
            }
        });
    }

    /**
     * Opens a file browse dialog
     */
    @FXML
    private void handleOpen() {
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            fileData.setFilePath(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleClose() {
        Platform.exit();
    }

    @FXML
    private void handleMaximise() {
        if(stage == null){
            stage = (Stage) titleBar.getScene().getWindow();
        }
//        if(stage.isMaximized()){
//            stage.setMaximized(false);
//        } else {
//            stage.setMaximized(true);
//        }
        stage.setFullScreen(true);
    }

    @FXML
    private void handleMinimise() {
        if(stage == null){
            stage = (Stage) titleBar.getScene().getWindow();
        }
        if(stage.isIconified()){
            stage.setIconified(false);
        } else {
            stage.setIconified(true);
        }
    }
}
