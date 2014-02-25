package com.LPSWorkflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for displaying database status (databaseView)
 */
public class DatabaseController implements Initializable{
    @FXML
    private TextArea factField;
    @FXML
    private Pane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // For each fluent in the program, add a checkbox (ticked box will mean it holds)
        factField.prefWidthProperty().bind(contentPane.widthProperty());
    }
}
