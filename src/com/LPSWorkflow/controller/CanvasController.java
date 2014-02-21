package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.visualComponent.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    private List<String> fluents;
    private FileData fileData;
    private LPSFileManager fileManager;
    private Map<Entity, Node> displayMap; // stores mappings from Entities to corresponding Nodes
    private Map<String,Entity> entityMap;

    @FXML
    private GridPane parentGridPane;

    @FXML
    private Pane contentPane; // TODO BorderPane?

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        displayMap = new HashMap<Entity, Node>();

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
        contentPane.getChildren().clear();
        entityMap = fileManager.getEntityMap();
        fluents = fileManager.getFluents();

        final HBox resultHBox = new HBox();
        Group resultGroup;
        double initX = 0;
        double initY = 0;

        //for each root entity, go through the chain and build the workflow diagram
        for(Entity rootEntity : entityMap.values()){
            resultGroup = new Group();
            buildWorkflowDiagram(resultGroup, rootEntity, initX, initY, true);
            resultHBox.getChildren().add(resultGroup);
            //initX += resultGroup.getLayoutBounds().getWidth() + Constants.NODE_HORIZONTAL_GAP; TODO
        }
        contentPane.getChildren().add(resultHBox);

        //TODO allow SPACE + mouse-drag as well?
        // move view point using scroll
        contentPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                double translateX = e.getDeltaX();
                double translateY = e.getDeltaY();
                resultHBox.setTranslateX(resultHBox.getTranslateX() + translateX);
                resultHBox.setTranslateY(resultHBox.getTranslateY() + translateY);
            }
        });
    }

    private void buildWorkflowDiagram(Group resultGroup, Entity rootEntity, double initX, double initY, boolean drawRoot) {
        double nextX = initX;
        double nextY = initY;

        if(rootEntity == null || resultGroup == null){
            return;
        }

        Entity currEntity = rootEntity;
        Entity nextEntity;
        Node currNode;
        Node nextNode;
        // draw the root first
        if(drawRoot){
            currNode = createNodeFor(currEntity, initX, initY);
            resultGroup.getChildren().add(currNode);
        }

//        //TODO
//        if(entityMap.values().contains(rootEntity)){
//            // TODO It is a root of a reactive rule. Draw a special arrow
//        }

        while(currEntity != null){
            nextY += Constants.NODE_HEIGHT + Constants.NODE_VERTICAL_GAP;
            nextEntity = currEntity.getNext();
            // Draw next node
            currNode = displayMap.get(currEntity);

            if(nextEntity == null){
                // if next is null, check if current entity is a multiChildEntity
                if(!currEntity.hasSingleChild()){
                    // if current node is a multiChildEntity, next should be its 'nextEntities'.
                    List<Entity> nextEntities = ((MultiChildEntity) currEntity).getNextEntities();
                    int n = nextEntities.size();
                    double totalWidth = ((n-1)*Constants.NODE_HORIZONTAL_GAP) + (n* Constants.NODE_WIDTH);
                    nextX -= ((totalWidth/2) - (Constants.NODE_WIDTH/2));
                    // spread them out and draw each path
                    // TODO possibly taking care of partial ordering in a similar way?
                    for(Entity child : nextEntities){
                        buildWorkflowDiagram(resultGroup, child, nextX, nextY, true);
                        nextX += Constants.NODE_WIDTH + Constants.NODE_HORIZONTAL_GAP;
                        resultGroup.getChildren().add(new Arrow(currNode, displayMap.get(child)));
                    }
                    //TODO use getLayoutBounds().getWidth() from resultGroup each time to get the next y?

                    nextX = currNode.getLayoutX();
                    nextY = resultGroup.getLayoutBounds().getHeight() + Constants.NODE_VERTICAL_GAP;
                } else {
                    // otherwise, there is no next entity, so stop.
                    break;
                }
            } else {
                // draw the next entity TODO
                nextNode = createNodeFor(nextEntity, nextX, nextY);
                resultGroup.getChildren().add(nextNode);
                // todo could be the beginning of a multiChildEntity. need a special case?

                // Draw connection from prev to current node TODO
                resultGroup.getChildren().add(new Arrow(currNode, nextNode));
            }

            // Update currEntity and continue
            currEntity = nextEntity;
        }
    }

    // Create a Node for given Entity, and store the mapping in displayMap.
    private Node createNodeFor(Entity entity, double x, double y) {
        String name = entity.getName();
        Node node;
        if(!entity.hasSingleChild()){ // a MultiChildEntity
            if(entity.getName().equals("OR")){ //TODO use Enum to distinguish?
                node = new OrNode();
            } else/* if(entity.getName().equals("AND")) */{
                node = new AndNode();
            }
        } else if(isFluent(name, fluents)) {
            node = new FluentNode(name);
        } else {
            Group goalDef = new Group();
            buildWorkflowDiagram(goalDef, entity.getDefinition(), 0, 0, false);
            node = new ActionNode(name, goalDef);
        }
        node.setLayoutX(x);
        node.setLayoutY(y);
        displayMap.put(entity, node);
        return node;
    }

    private boolean isFluent(String currName, List<String> fluents) {
        return fluents.contains(currName)
                || (currName.contains("!") && fluents.contains(currName.substring(1))) // negation
                || (currName.contains(":")); // concurrent
    }
}
