package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.abstractComponent.Or;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.visualComponent.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
        VBox resultVBox = new VBox();

        if(rootEntity == null){
            return resultVBox; // TODo return null? will give an exception when trying to add it to rootsHBox above
        }

        resultVBox.setAlignment(Pos.TOP_CENTER);
        resultVBox.setPadding(new Insets(Constants.PADDING_WIDTH));
        resultVBox.getChildren().add(new Arrow()); // initial entry arrow

        Entity currentEntity = rootEntity;

        while(currentEntity != null){
            String currName = currentEntity.getName();
            Node currNode;

            if(currName.equals("OR") || currName.equals("AND")){  //TODO better way to distinguish multi-child entities?
                if(currName.equals("OR")){
                    currNode = new OrNode(currName);
                } else {
                    currNode = new AndNode(currName);
                }

                // draw the node and an arrow
                resultVBox.getChildren().addAll(currNode, new Arrow());

                HBox optionsHBox = new HBox();
                HBox optionsArrowHBox = new HBox();
                optionsHBox.setAlignment(Pos.CENTER);
                optionsArrowHBox.setAlignment(Pos.CENTER);

                List<Entity> optionEntities = ((MultiChildEntity) currentEntity).getEntities();

                for(Entity option : optionEntities){
                    VBox optionChain = buildWorkflowDiagram(option, fluents);
                    optionsArrowHBox.getChildren().add(new Arrow(/*currNode, optionChain*/));
                    optionsHBox.getChildren().add(optionChain);
                }
                resultVBox.getChildren().add(optionsArrowHBox);
                resultVBox.getChildren().add(optionsHBox);
            } else {
                if(isFluent(currName, fluents)){
                    currNode = new FluentNode(currName);
                } else {
                    currNode = new ActionNode(currName, buildWorkflowDiagram(currentEntity.getDefinition(), fluents));
                }

                // draw the node and an arrow
                if(fileManager.getEntityMap().keySet().contains(currName)){
                    // use a special arrow for antecedent of a reactive rule
                    resultVBox.getChildren().addAll(currNode, new ReactiveArrow());
                } else {
                    resultVBox.getChildren().addAll(currNode, new Arrow());
                }
            }

            currentEntity = currentEntity.getNext();
        }
        //remove the first redundant arrow
        resultVBox.getChildren().remove(0);
        return resultVBox;
    }

    private boolean isFluent(String currName, List<String> fluents) {
        return fluents.contains(currName)
                || (currName.contains("!") && fluents.contains(currName.substring(1))) // negation
                || (currName.contains(":")); // concurrent
    }


    // TODO clear up
//    //TODO crude angle calculation.... replace with actual start and end coordinates
//    private double[] calculateAngleOfArrow(int childCount) {
//        // assume height of an entity is 40, width of arrow is 50 for simplicity
//        double[] resultArray = new double[childCount];
//        int numHeights = childCount - 1;
//
//        double maxAngle = Math.toDegrees(Math.atan((numHeights * 60) / 50.0));
//
//        double intervalAngle = maxAngle/(childCount/2);
//
//        for(int i = 0; i < childCount/2; i++){
//            resultArray[i] = maxAngle - (intervalAngle * i);
//            resultArray[childCount - i - 1] = resultArray[i] * -1;
//        }
//
//        //if odd number, need to put the centre arrow
//        if(childCount % 2 == 1){
//            resultArray[((childCount + 1)/2) + 1] = 0;
//        }
//
//        return resultArray;
//    }
}
