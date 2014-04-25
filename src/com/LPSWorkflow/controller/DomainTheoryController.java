package com.LPSWorkflow.controller;

import com.LPSWorkflow.model.domainTheory.DomainTheoryData;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import com.LPSWorkflow.model.domainTheory.Precondition;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for domainTheoryView
 */
public class DomainTheoryController implements Initializable{
    @FXML private ScrollPane domainTheoryScrollPane;
    @FXML private Group group;
    @FXML private VBox domainTheoryPane;
    @FXML private Label precondLabel;
    @FXML private Label postcondLabel;
    private DomainTheoryData domainTheoryData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        domainTheoryPane.prefWidthProperty().bind(domainTheoryScrollPane.widthProperty());
        domainTheoryPane.prefHeightProperty().bind(domainTheoryScrollPane.heightProperty());

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

        domainTheoryPane.setOnZoom(zoomEvent -> {
            double scale = domainTheoryPane.getScaleX() * zoomEvent.getZoomFactor();
            if(scale < 1){
                scale = 1;
            }
            domainTheoryPane.setScaleX(scale);
            domainTheoryPane.setScaleY(scale);
        });
    }
}
