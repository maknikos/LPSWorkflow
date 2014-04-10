package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.model.abstractComponent.Entity;
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
    @FXML private Label precondTitleLabel;
    @FXML private Label precondLabel;
    @FXML private Label postcondTitleLabel;
    @FXML private Label postcondLabel;
    @FXML private Label title;
    private LPSFileManager fileManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setStyle("-fx-font-size:20px");
        precondTitleLabel.setStyle("-fx-border-radius:3px; -fx-border-color:black; -fx-padding:2");
        postcondTitleLabel.setStyle("-fx-border-radius:3px; -fx-border-color:black; -fx-padding:2");

        precondLabel.setStyle("-fx-font-size:15px; -fx-alignment:top-left; -fx-text-alignment:left; -fx-padding:5px");
        postcondLabel.setStyle("-fx-font-size:15px; -fx-alignment:top-left; -fx-text-alignment:left; -fx-padding:5px");

        fileManager = LPSFileManager.getInstance();
        fileManager.isFileOpenProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(fileManager.isFileOpen()){
                    // add preconditions
                    List<List<Entity>> preconditions = fileManager.getPreconditions();
                    String precondContent = "";
                    for(List<Entity> precondition : preconditions){
                        String conjunction = "";
                        for(Entity e : precondition){
                            conjunction = conjunction.concat(e.getName() + " & ");
                        }
                        conjunction = conjunction.substring(0, conjunction.length() - 2);
                        precondContent = precondContent.concat(String.format("false <- %s \n", conjunction));
                    }
                    precondLabel.setText(precondContent);

                    // add postconditions
                    List<Postcondition> postconditions = fileManager.getPostconditions();
                    String postcondContent = "";
                    for(Postcondition p : postconditions){
                        postcondContent = postcondContent.concat(p.toString() + "\n");
                    }
                    postcondLabel.setText(postcondContent);
                }


            }
        });
    }
}
