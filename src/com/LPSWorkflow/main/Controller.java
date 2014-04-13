package com.LPSWorkflow.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Pane mainPane;

    @FXML
    private SplitPane mainSplitPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //manually bind width and height so that it fills the parent pane
        mainSplitPane.prefWidthProperty().bind(mainPane.widthProperty());
        mainSplitPane.prefHeightProperty().bind(mainPane.heightProperty());
    }
}
