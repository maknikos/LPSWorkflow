package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.ExecutionManager;
import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.FileData;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.Fluent;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.execution.Token;
import com.LPSWorkflow.model.execution.TokenShape;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageType;
import com.LPSWorkflow.model.visualComponent.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    private FileData fileData;
    private LPSFileManager fileManager;
    private MessageData messageData;
    private Map<Entity, Node> displayMap; // stores mappings from Entities to corresponding Nodes
    private Map<String,Entity> entityMap;
    private Map<Node, Set<Arrow>> arrowsFrom; // arrows (value) from the node (key)
    private Map<Node, Set<Arrow>> arrowsTo; // arrows (value) connected to the node (key) (used for merging multiple arrows)
    private boolean diagramDrawn;
    private HBox diagramLayer;
    private Group executionLayer;
    private ExecutionManager execManager;

    private double currentScale;
    private DoubleProperty scaleProperty; // unified scale factor for all layers
    private DoubleProperty translateXProperty; // unified translate value in X axis
    private DoubleProperty translateYProperty; // unified translate value in Y axis

    @FXML
    private Pane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentScale = 1.0;
        scaleProperty = new SimpleDoubleProperty(1.0);
        translateXProperty = new SimpleDoubleProperty(0.0);
        translateYProperty = new SimpleDoubleProperty(0.0);
        messageData = MessageData.getInstance();
        fileData = FileData.getInstance();
        displayMap = new HashMap<>();
        arrowsFrom = new HashMap<>();
        arrowsTo = new HashMap<>();
        diagramDrawn = false;
        diagramLayer = new HBox();
        executionLayer = new Group();
        contentPane.getChildren().addAll(diagramLayer, executionLayer);
        fileManager = LPSFileManager.getInstance();

        clipViewingRegion();
        setLayerBindings();
        setEventHandlers();
    }

    private void setEventHandlers() {
        //TODO allow SPACE + mouse-drag as well?
        //TODO smooth scrolling?
        // TODO mini-map
        // move view point using scroll
        contentPane.setOnScroll((ScrollEvent e) -> {
            double translateX = translateXProperty.get() + e.getDeltaX();
            double translateY = translateYProperty.get() + e.getDeltaY();
            double addedValue = 100;

            double maxX = contentPane.getLayoutBounds().getMaxX();
            double minX = contentPane.getLayoutBounds().getMinX();
            double maxY = contentPane.getLayoutBounds().getMaxY();
            double minY = contentPane.getLayoutBounds().getMinY();
            double width = diagramLayer.getWidth();
            double height = diagramLayer.getHeight();

            // limit scroll region
            if(translateX + addedValue > maxX){
                translateX = maxX - addedValue;
            } else if (translateX + width + addedValue < minX) {
                translateX = minX - width - addedValue;
            }
            if(translateY + addedValue > maxY){
                translateY = maxY - addedValue;
            } else if (translateY + height + addedValue < minY) {
                translateY = minY - height - addedValue;
            }

            translateXProperty.set(translateX);
            translateYProperty.set(translateY);
        });

        // TODO allow ctrl+scroll (or something similar) to zoom... for systems without Pinch-zoom capability
        // TODO or introduce a slider/combobox (**% zoom)
        contentPane.setOnZoomFinished(zoomEvent -> {
            currentScale = scaleProperty.get();
        });

        contentPane.setOnZoom((ZoomEvent zoomEvent) -> {
            double zoomFactor = currentScale * zoomEvent.getTotalZoomFactor();

            // limit the scale
            if(zoomFactor > Constants.SCALE_UPPER_LIMIT){
                zoomFactor = Constants.SCALE_UPPER_LIMIT ;
            } else if (zoomFactor < Constants.SCALE_LOWER_LIMIT ){
                zoomFactor = Constants.SCALE_LOWER_LIMIT ;
            }
            scaleProperty.set(zoomFactor);
        });
    }

    private void setLayerBindings() {
        diagramLayer.translateXProperty().bind(translateXProperty);
        diagramLayer.translateYProperty().bind(translateYProperty);
        executionLayer.translateXProperty().bind(translateXProperty);
        executionLayer.translateYProperty().bind(translateYProperty);
        diagramLayer.scaleXProperty().bind(scaleProperty);
        diagramLayer.scaleYProperty().bind(scaleProperty);
        executionLayer.scaleXProperty().bind(scaleProperty);
        executionLayer.scaleYProperty().bind(scaleProperty);
    }

    private void clipViewingRegion() {
        Rectangle clip = new Rectangle(0,0,0,0);
        clip.widthProperty().bind(contentPane.widthProperty());
        clip.heightProperty().bind(contentPane.heightProperty());
        contentPane.setClip(clip);
    }

    @FXML
    private void handleDrawAction() {
        fileManager.openFile(fileData.getFilePath());

        //TODO if nothing drawn, display what to do ("no file open... use... to open ...", etc.)

        //only draw when the file is open
        if(fileManager.isFileOpen()){
            drawProgram();
        } else {
            messageData.sendMessage("No file opened yet. Use File->Open to select a program file.", MessageType.ERROR);
        }
    }

    @FXML
    private void handleNextAction(){ //TODO
        if(diagramDrawn){
            executionLayer.getChildren().clear();
            List<Token> agents = execManager.getNextStep();

            for(Token agent : agents){
                TokenShape circle = new TokenShape();

                Node node = displayMap.get(agent.getCurrentEntity());
                circle.setCenterX(node.getParent().getBoundsInParent().getMinX() + node.getBoundsInParent().getMaxX());
                circle.setCenterY(node.getBoundsInParent().getMinY());
                executionLayer.getChildren().add(circle);
            }
        } else {
            messageData.sendMessage("No LPS program is drawn yet. Nothing to execute.", MessageType.ERROR);
        }
    }

    private void initDraw(){
        diagramLayer.getChildren().clear();
        executionLayer.getChildren().clear();
        entityMap = fileManager.getRootMap();
        execManager = new ExecutionManager(entityMap);
    }

    private void drawProgram() {
        initDraw();

        Group resultGroup;
        double initX = 0;
        double initY = 0;

        //for each root entity, go through the chain and build the workflow diagram
        for(Entity rootEntity : entityMap.values()){
            resultGroup = new Group();
            buildWorkflowDiagram(resultGroup, rootEntity, initX, initY, true);
            diagramLayer.getChildren().add(resultGroup);
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
                nextX -= Constants.NODE_HORIZONTAL_GAP/2;

                // spread them out and draw each path
                for(Entity child : nextEntities){
                    buildWorkflowDiagram(resultGroup, child, nextX, nextY, true);
                    nextX = resultGroup.getLayoutBounds().getMaxX() + Constants.NODE_HORIZONTAL_GAP;
                    resultGroup.getChildren().add(createArrow(currNode, displayMap.get(child), true));
                }

                nextX = currNode.getLayoutX();
                nextY = resultGroup.getLayoutBounds().getHeight() + Constants.NODE_VERTICAL_GAP;

                // if it has next entity, need to merge all paths to point to that.
                nextNode = createNodeFor(nextEntity, nextX, nextY);

                if(nextNode == null){
                    break; // finish if there is no next node.
                }

                // for the last node in each path for the multiChildEntity, connect arrows
                resultGroup.getChildren().add(nextNode);
                for(Entity next : nextEntities){
                    Entity last = getLastEntityInThePath(next);
                    Node lastNode = displayMap.get(last);
                    resultGroup.getChildren().add(createArrow(lastNode, nextNode, true));
                }

            } else if(currEntity.getType() == EntityType.FLUENT
                    && ((Fluent)currEntity).getFalseNext() != null) {
                // for a fluent with FalseNext, we need to draw both True and False cases.
                Fluent fluent = (Fluent) currEntity;
                Entity trueNext = fluent.getNext();
                Entity falseNext = fluent.getFalseNext();

                if(trueNext != null){
                    // need to draw both
                    nextX -= Constants.NODE_HORIZONTAL_GAP/2;
                    // spread them out and draw each path
                    //Draw True branch
                    buildWorkflowDiagram(resultGroup, trueNext, nextX, nextY, true);
                    nextX += Constants.NODE_WIDTH + Constants.NODE_HORIZONTAL_GAP;
                    resultGroup.getChildren().add(createArrow(currNode, displayMap.get(trueNext), true));
                    //Draw False branch
                    buildWorkflowDiagram(resultGroup, falseNext, nextX, nextY, true);
                    resultGroup.getChildren().add(createArrow(currNode, displayMap.get(falseNext), false));

                    // nothing to merge, so finish.
                    break;
                } else {
                    // only has falseNext
                    nextEntity = falseNext;
                    nextNode = createNodeFor(nextEntity, nextX, nextY);
                    resultGroup.getChildren().add(nextNode);

                    if(entityMap.values().contains(currEntity)){
                        // it is antecedent of a reactive rule.
                        resultGroup.getChildren().add(new ReactiveArrow(currNode, nextNode));
                    } else {
                        // Draw connection from prev to current node
                        resultGroup.getChildren().add(createArrow(currNode, nextNode, false));
                    }
                }

                // select the next entity TODO

            } else if(nextEntity == null){
                // there is no next entity, so stop.
                break;
            } else {
                // draw the next entity
                nextNode = createNodeFor(nextEntity, nextX, nextY);
                resultGroup.getChildren().add(nextNode);

                if(entityMap.values().contains(currEntity)){
                    // it is antecedent of a reactive rule.
                    resultGroup.getChildren().add(new ReactiveArrow(currNode, nextNode));
                } else {
                    // Draw connection from prev to current node
                    resultGroup.getChildren().add(createArrow(currNode, nextNode, true));
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

    private Arrow createArrow(Node nodeFrom, Node nodeTo, boolean arrowForTrue){
        Set<Arrow> fromSet = arrowsFrom.get(nodeFrom);
        Set<Arrow> toSet = arrowsTo.get(nodeTo);
        if(fromSet == null){
            fromSet = new HashSet<>();
            arrowsFrom.put(nodeFrom, fromSet);
        }
        if(toSet == null){
            toSet = new HashSet<>();
            arrowsTo.put(nodeTo, toSet);
        }
        Arrow arrow = new Arrow(nodeFrom, nodeTo, toSet, arrowForTrue);
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

        //TODO consider using Switch cases
        if(!entity.hasSingleChild()){ // a MultiChildEntity
            if(entity.getType() == EntityType.OR){
                node = new OrNode();
            } else if(entity.getType() == EntityType.AND) {
                node = new AndNode();
            } else if(entity.getType() == EntityType.PARTIAL_ORDER) {
                node = new PartialOrderNode(); // TODO if the shapes are the same, we can use MultiChildNode for all three. (left distinct in case their shape may differ)
            }
        } else if(entity.getType() == EntityType.FLUENT) {
            node = new FluentNode(name);
        } else if(entity.getType() == EntityType.EXIT){
            node = new ExitNode();
        } else { // for Action entity
            Group goalDef = new Group();
            buildWorkflowDiagram(goalDef, entity.getDefinition(), 0, 0, false);
            node = new ActionNode(name, goalDef);
        }
        node.setLayoutX(x);
        node.setLayoutY(y);
        displayMap.put(entity, node);
        return node;
    }
}
