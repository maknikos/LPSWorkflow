package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for domainTheoryView
 */
public class DomainTheoryController implements Initializable{
    @FXML
    private Label title;
    @FXML
    private Label domainTheoryLabel;

    private LPSFileManager fileManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setStyle("-fx-font-size:20px");
        domainTheoryLabel.setStyle("-fx-font-size:15px; -fx-alignment:top-left; -fx-text-alignment:left");
        fileManager = LPSFileManager.getInstance();
        fileManager.isFileOpenProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                //TODO
                List<Postcondition> postconditions = fileManager.getPostconditions();
                String content = "";
                for(Postcondition p : postconditions){
                    content = content.concat(p.toString() + "\n");
                }
                domainTheoryLabel.setText(content);
            }
        });
    }
}
