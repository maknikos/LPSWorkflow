package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for displaying database status (databaseView)
 */
public class DatabaseController implements Initializable{
    private Database database;

    @FXML
    private TextArea factArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // For each fluent in the program, add a checkbox (ticked box will mean it holds)
        database = Database.getInstance();
        database.factsProperty().bind(factArea.textProperty());
    }
}
