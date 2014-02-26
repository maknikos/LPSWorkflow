package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.ExecutionManager;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.net.URL;
import java.util.*;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    private List<String> fluents;
    private FileData fileData;
    private LPSFileManager fileManager;
    private Map<Entity, Node> displayMap; // stores mappings from Entities to corresponding Nodes
    private Map<String,Entity> entityMap;
    private Map<Node, Set<Arrow>> arrowsFrom; // arrows (value) from the node (key)
    private Map<Node, Set<Arrow>> arrowsTo; // arrows (value) connected to the node (key)
    private boolean diagramDrawn;
    private HBox diagramLayer;
    private Group executionLayer;
    private ExecutionManager execManager;
    private Circle execCircle;

    @FXML
    private Pane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        displayMap = new HashMap<Entity, Node>();
        arrowsFrom = new HashMap<Node, Set<Arrow>>();
        arrowsTo = new HashMap<Node, Set<Arrow>>();
        diagramDrawn = false;
        diagramLayer = new HBox();
        executionLayer = new Group();

        // Use the custom LPS parser to get data
        fileManager = new LPSFileManager();

        contentPane.getChildren().addAll(diagramLayer, executionLayer);

        // set clip (viewing region)
        Rectangle clip = new Rectangle(0,0,0,0);
        clip.widthProperty().bind(contentPane.widthProperty());
        clip.heightProperty().bind(contentPane.heightProperty());
        contentPane.setClip(clip);

        //TODO allow SPACE + mouse-drag as well?
        // move view point using scroll
        contentPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                double translateX = e.getDeltaX();
                double translateY = e.getDeltaY();
                diagramLayer.setTranslateX(diagramLayer.getTranslateX() + translateX);
                diagramLayer.setTranslateY(diagramLayer.getTranslateY() + translateY);
                executionLayer.setTranslateX(executionLayer.getTranslateX() + translateX);
                executionLayer.setTranslateY(executionLayer.getTranslateY() + translateY);
            }
        });
    }

    @FXML
    private void handleDrawAction() {
        fileManager.openFile(fileData.getFilePath());

        //only draw when the file is open
        if(fileManager.isFileOpen()){
            drawProgram();
        } else {
            //TODO error message
        }
    }

    @FXML
    private void handleNextAction(){
        if(diagramDrawn){
            // TODO if multiple paths are possible, spawn new position circles
            if(execCircle == null){
                execCircle = createExecCircle();
                executionLayer.getChildren().add(execCircle);
            }

            Entity nextEntity = execManager.getNextEntity();
            if(nextEntity == null){
                return; // TODO end? special case?
            } else {
                Node node = displayMap.get(nextEntity);
                execCircle.setCenterX(node.getParent().getBoundsInParent().getMinX() + node.getBoundsInParent().getMaxX());
                execCircle.setCenterY(node.getBoundsInParent().getMaxY());
            }


        } else {
            //TODO Error message
        }
    }

    private Circle createExecCircle() { // TODO make into class
        Circle circle = new Circle(10);
        circle.setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
        circle.setStroke(Color.GOLD);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.OUTSIDE);
        return circle;
    }


    private void drawProgram() {
        diagramLayer.getChildren().clear();
        entityMap = fileManager.getRootMap();
        execManager = new ExecutionManager(entityMap); //TODO group initialisations?
        fluents = fileManager.getFluents();

        Group resultGroup;
        double initX = 0;
        double initY = 0;

        //for each root entity, go through the chain and build the workflow diagram
        for(Entity rootEntity : entityMap.values()){
            resultGroup = new Group();
            buildWorkflowDiagram(resultGroup, rootEntity, initX, initY, true);
            diagramLayer.getChildren().add(resultGroup);
            //initX += resultGroup.getLayoutBounds().getWidth() + Constants.NODE_HORIZONTAL_GAP; TODO
        }

        diagramDrawn = true;
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

        while(currEntity != null){
            nextY += Constants.NODE_HEIGHT + Constants.NODE_VERTICAL_GAP;
            nextEntity = currEntity.getNext();
            // Draw next node
            currNode = displayMap.get(currEntity);

            if(!currEntity.hasSingleChild()){
                // if current node is a multiChildEntity, next should be its 'nextEntities'.
                List<Entity> nextEntities = ((MultiChildEntity) currEntity).getNextEntities();
                int n = nextEntities.size();
                double totalWidth = ((n-1)*Constants.NODE_HORIZONTAL_GAP) + (n* Constants.NODE_WIDTH);
                nextX -= ((totalWidth/2) - (Constants.NODE_WIDTH/2));
                // spread them out and draw each path
                for(Entity child : nextEntities){
                    buildWorkflowDiagram(resultGroup, child, nextX, nextY, true);
                    nextX += Constants.NODE_WIDTH + Constants.NODE_HORIZONTAL_GAP;
                    resultGroup.getChildren().add(createArrow(currNode, displayMap.get(child)));
                }
                //TODO use getLayoutBounds().getWidth() from resultGroup each time to get the next y?

                nextX = currNode.getLayoutX();
                nextY = resultGroup.getLayoutBounds().getHeight() + Constants.NODE_VERTICAL_GAP;

                // if it has next entity, need to merge all paths to point to that.
                nextNode = createNodeFor(nextEntity, nextX, nextY);
                if(nextNode == null){
                    nextNode = new EmptyNode();
                }
                resultGroup.getChildren().add(nextNode);

                // for the last node in each path for the multiChildEntity, connect arrows
                for(Entity next : nextEntities){
                    Entity last = getLastEntityInThePath(next);
                    Node lastNode = displayMap.get(last);
                    resultGroup.getChildren().add(createArrow(lastNode, nextNode));
                }
            } else if(nextEntity == null){
                // there is no next entity, so stop.
                break;
            } else {
                // draw the next entity TODO
                nextNode = createNodeFor(nextEntity, nextX, nextY);
                resultGroup.getChildren().add(nextNode);

                if(entityMap.values().contains(currEntity)){
                    // it is antecedent of a reactive rule.
                    resultGroup.getChildren().add(new ReactiveArrow(currNode, nextNode));
                } else {
                    // Draw connection from prev to current node
                    resultGroup.getChildren().add(createArrow(currNode, nextNode));
                }
            }

            // Update currEntity and continue
            currEntity = nextEntity;
        }
    }

    private Entity getLastEntityInThePath(Entity entity) {
        Entity result = entity;
        if(result == null){
            return null;
        }
        while(result.getNext() != null){
            result = result.getNext();
        }
        return result;
    }

    private Arrow createArrow(Node nodeFrom, Node nodeTo){
        Set<Arrow> fromSet = arrowsFrom.get(nodeFrom);
        Set<Arrow> toSet = arrowsTo.get(nodeTo);
        if(fromSet == null){
            fromSet = new HashSet<Arrow>();
            arrowsFrom.put(nodeFrom, fromSet);
        }
        if(toSet == null){
            toSet = new HashSet<Arrow>();
            arrowsTo.put(nodeTo, toSet);
        }
        Arrow arrow = new Arrow(nodeFrom, nodeTo, toSet);
        fromSet.add(arrow);
        toSet.add(arrow);
        return arrow;
    }

    // Create a Node for given Entity, and store the mapping in displayMap.
    private Node createNodeFor(Entity entity, double x, double y) {
        if(entity == null){
            return null;
        }
        String name = entity.getName();
        Node node = null;
        if(!entity.hasSingleChild()){ // a MultiChildEntity
            if(entity.getName().equals("OR")){ //TODO use Enum to distinguish?
                node = new OrNode();
            } else if(entity.getName().equals("AND")) {
                node = new AndNode();
            } else if(entity.getName().equals("||")) {
                node = new PartialOrderNode(); // TODO if the shapes are the same, we can use MultiChildNode for all three. (left distinct in case their shape may differ)
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
