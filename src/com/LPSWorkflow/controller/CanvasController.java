package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.model.abstractComponent.Choice;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.visualComponent.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {

    private FileData fileData;
    private LPSFileManager fileManager;

    @FXML
    private GridPane parentGridPane;

    @FXML
    private ScrollPane contentScrollPane;

    @FXML
    private Label filePathLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        filePathLabel.textProperty().bind(fileData.filePathProperty());

        // Use the custom LPS parser to get data
        fileManager = new LPSFileManager();
        ColumnConstraints cc = new ColumnConstraints();
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        parentGridPane.getColumnConstraints().add(cc);
    }

    //TODO change the name
    public void handleDrawAction() {
        fileManager.openFile(fileData.getFilePath());

        //only draw when the file is open
        if(fileManager.isFileOpen()){
            drawProgram();
        }
    }

    private void drawProgram() {
        Map<String,Entity> entityMap = fileManager.getEntityMap();
        List<String> fluents = fileManager.getFluents();

        // each independent horizontal chain is stacked vertically
        HBox rootsHBox = new HBox();
        rootsHBox.setAlignment(Pos.CENTER);

        //for each root entity, go through the chain and build the workflow diagram
        for(Entity rootEntity : entityMap.values()){
            rootsHBox.getChildren().add(buildWorkflowDiagram(rootEntity, fluents));
        }

        contentScrollPane.setContent(rootsHBox);
    }

    private VBox buildWorkflowDiagram(Entity rootEntity, List<String> fluents) {
        if(rootEntity == null){
            return null;
        }

        VBox resultVBox = new VBox();
        resultVBox.setAlignment(Pos.TOP_CENTER);
        resultVBox.setStyle("-fx-padding:12px");

        Entity currentEntity = rootEntity;

        while(currentEntity != null){
            String currName = currentEntity.getName();
            Node currNode = null;

            if(currName.equals("OR")){
                currNode = new ChoiceNode(currName);
                resultVBox.getChildren().addAll(new Arrow(0), currNode);

                HBox optionsHBox = new HBox();
                HBox optionsArrowHBox = new HBox();
                optionsHBox.setAlignment(Pos.CENTER);
                optionsArrowHBox.setAlignment(Pos.CENTER);

                List<Entity> optionEntities = ((Choice) currentEntity).getEntities();
                double[] angles = calculateAngleOfArrow(optionEntities.size());
                for(int i = 0; i < angles.length; i++){
                    optionsArrowHBox.getChildren().add(new Arrow(angles[i]));
                }

                for(Entity option : optionEntities){
                    optionsHBox.getChildren().add(buildWorkflowDiagram(option, fluents));
                }
                resultVBox.getChildren().add(optionsArrowHBox);
                resultVBox.getChildren().add(optionsHBox);
            } else {
                if(fluents.contains(currName)){
                    currNode = new FluentNode(currName);
                } else {
                    currNode = new ActionNode(currName, buildWorkflowDiagram(currentEntity.getDefinition(), fluents));
                }
                resultVBox.getChildren().addAll(new Arrow(0), currNode);
            }

            currentEntity = currentEntity.getNext();
        }
        //remove the first redundant arrow
        resultVBox.getChildren().remove(0);
        return resultVBox;
    }

    //TODO crude angle calculation.... replace with actual start and end coordinates
    private double[] calculateAngleOfArrow(int childCount) {
        // assume height of an entity is 40, width of arrow is 50 for simplicity
        double[] resultArray = new double[childCount];
        int numHeights = childCount - 1;

        double maxAngle = Math.toDegrees(Math.atan((numHeights * 60) / 50.0));

        double intervalAngle = maxAngle/(childCount/2);

        for(int i = 0; i < childCount/2; i++){
            resultArray[i] = maxAngle - (intervalAngle * i);
            resultArray[childCount - i - 1] = resultArray[i] * -1;
        }

        //if odd number, need to put the centre arrow
        if(childCount % 2 == 1){
            resultArray[((childCount + 1)/2) + 1] = 0;
        }

        return resultArray;
    }
}
