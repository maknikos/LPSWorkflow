package com.LPSWorkflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for domainTheoryView
 */
public class DomainTheoryController implements Initializable{
    @FXML
    private Label title;
    @FXML
    private Label domainTheoryLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setStyle("-fx-font-size:20px");
        domainTheoryLabel.setStyle("-fx-font-size:15px; -fx-alignment:top-left; -fx-text-alignment:left");

    }
}
