package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.domainTheory.DomainTheoryData;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import com.LPSWorkflow.model.domainTheory.Precondition;
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
    @FXML private Label precondLabel;
    @FXML private Label postcondLabel;
    private DomainTheoryData domainTheoryData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        domainTheoryData = DomainTheoryData.getInstance();
        domainTheoryData.preconditionsProperty().addListener((Observable observable) -> {
            // add preconditions
            List<Precondition> preconditions = domainTheoryData.getPreconditions();
            String precondContent = "";
            for (Precondition precondition : preconditions) {
                precondContent = precondContent.concat(precondition.toString() + "\n");
            }
            precondLabel.setText(precondContent);
        });

        domainTheoryData.postconditionsProperty().addListener((Observable observable) -> {
            // add postconditions
            List<Postcondition> postconditions = domainTheoryData.getPostconditions();
            String postcondContent = "";
            for(Postcondition p : postconditions){
                postcondContent = postcondContent.concat(p.toString() + "\n");
            }
            postcondLabel.setText(postcondContent);
        });
    }
}
