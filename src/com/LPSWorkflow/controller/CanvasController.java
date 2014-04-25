package com.LPSWorkflow.controller;

import com.LPSWorkflow.LPS.ExecutionManager;
import com.LPSWorkflow.LPS.LPSFileManager;
import com.LPSWorkflow.common.Constants;
import com.LPSWorkflow.common.DisplayMode;
import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.Fluent;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.execution.Token;
import com.LPSWorkflow.model.execution.TokenShape;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageType;
import com.LPSWorkflow.model.visualComponent.*;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.net.URL;
import java.util.*;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    @FXML private ToggleButton startToggleButton;
    @FXML private Pane contentPane;
    private LPSFileManager fileManager;
    private MessageData messageData;
    private Map<Entity, Node> entityDisplayMap; // stores mappings from Entities to corresponding Nodes
    private Map<Token, TokenShape> tokenDisplayMap; // stores mappings from Tokens to corresponding Shapes
    private Map<String,Entity> entityMap;
    private Map<Node, Set<Arrow>> arrowsFrom; // arrows (value) from the node (key)
    private Map<Node, Set<Arrow>> arrowsTo; // arrows (value) connected to the node (key) (used for merging multiple arrows)
    private boolean diagramDrawn;
    private HBox diagramLayer;
    private Pane executionLayer;
    private Pane layers;
    private ExecutionManager execManager;

    private DisplayMode displayMode;
    private Scale zoomScale;  // unified scale value
    private Translate translate;  // unified translate value

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageData = MessageData.getInstance();
        fileManager = LPSFileManager.getInstance();
        zoomScale = new Scale(1,1);
        translate = new Translate(0,0);
        entityDisplayMap = new HashMap<>();
        tokenDisplayMap = new HashMap<>();
        arrowsFrom = new HashMap<>();
        arrowsTo = new HashMap<>();
        diagramDrawn = false;
        diagramLayer = new HBox();
        executionLayer = new Pane();
        layers = new Pane(diagramLayer, executionLayer);
        contentPane.getChildren().addAll(layers);
        setDisplayMode(DisplayMode.VIEW);



        //TODO cleanup
//        executionLayer.setStyle("-fx-background-color: blue;");
//        diagramLayer.setStyle("-fx-background-color: yellow;");
//        layers.setStyle("-fx-background-color: yellow;");
//        contentPane.setStyle("-fx-background-color: green;");
//



        clipViewingRegion();
        setLayerBindings();
        setEventHandlers();
        initExecutionButton();
    }

    private void initExecutionButton() {
        startToggleButton.selectedProperty().addListener((observableValue, oldBool, buttonPressed) -> {
            if(buttonPressed){
                // start button pressed
                setDisplayMode(DisplayMode.EXECUTION);
                if(diagramDrawn){
                    executionLayer.getChildren().clear();
                    tokenDisplayMap.clear();

                    execManager.reset(entityMap);
                    execManager.getTokens().forEach(token -> {
                        Node node = entityDisplayMap.get(token.getCurrentEntity());
                        TokenShape tokenShape = new TokenShape(node);
                        tokenDisplayMap.put(token, tokenShape);
                        executionLayer.getChildren().add(tokenShape);
                    });

                    // change status of candidateTokens
                } else {
                    messageData.sendMessage("No LPS program is drawn yet. Nothing to execute.", MessageType.ERROR);
                    startToggleButton.setSelected(false);
                }
            } else {
                if(execManager != null){
                    execManager.clear();
                }
                setDisplayMode(DisplayMode.VIEW);
            }
        });
    }

    private void setDisplayMode(DisplayMode m) {
        switch(m){
            case VIEW:
                displayMode = DisplayMode.VIEW;
                startToggleButton.setText("Start");
                startToggleButton.setSelected(false);
                executionLayer.setVisible(false);
                break;
            case EXECUTION:
                displayMode = DisplayMode.EXECUTION;
                startToggleButton.setText("Stop");
                startToggleButton.setSelected(true);
                executionLayer.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void setEventHandlers() {
        //TODO allow SPACE + mouse-drag as well?
        //TODO smooth scrolling?
        // TODO mini-map
        // move view point using scroll
        contentPane.setOnScroll((ScrollEvent e) -> {
            double translateX = translate.getX() + e.getDeltaX();
            double translateY = translate.getY() + e.getDeltaY();
            setTranslate(translateX, translateY);
        });

        // TODO allow ctrl+scroll (or something similar) to zoom... for systems without Pinch-zoom capability
        // TODO or introduce a slider/combobox (**% zoom)
        contentPane.setOnZoom(zoomEvent -> {
            // scale about (0,0), then translate it by the increased amount in -x direction
            // ( newTx = previous_translation + increased_amount )
            Point2D mousePoint = layers.parentToLocal(zoomEvent.getX(), zoomEvent.getY());
            double s1 = zoomScale.getX();
            double s2 = zoomEvent.getZoomFactor();
            double tx1 = translate.getX();
            double ty1 = translate.getY();
            double tx2 = mousePoint.getX();
            double ty2 = mousePoint.getY();

            double newS = s1 * s2;
            double newTx = tx1 + (tx2 * (s1 - newS));
            double newTy = ty1 + (ty2 * (s1 - newS));

            boolean limitReached = false;
            // limit the scale
            if (newS > Constants.SCALE_UPPER_LIMIT) {
                newS = Constants.SCALE_UPPER_LIMIT;
                limitReached = true;
            } else if (newS < Constants.SCALE_LOWER_LIMIT) {
                newS = Constants.SCALE_LOWER_LIMIT;
                limitReached = true;
            }

            zoomScale.setX(newS);
            zoomScale.setY(newS);
            if(!limitReached){
                setTranslate(newTx, newTy);
            }
        });
    }

    private void setTranslate(double translateX, double translateY){
        Bounds contentPaneBounds = contentPane.getLayoutBounds();
        double maxX = contentPaneBounds.getMaxX();
        double minX = contentPaneBounds.getMinX();
        double maxY = contentPaneBounds.getMaxY();
        double minY = contentPaneBounds.getMinY();
        Bounds layersBounds = layers.getBoundsInParent();
        double layersWidth = layersBounds.getWidth();
        double layersHeight = layersBounds.getHeight();
        double padding = 50;

        // limit scroll region (about the centre of layers)
        if(translateX + padding > maxX){ // right
            translateX = maxX - padding;
        } else if(translateX + layersWidth - padding < minX){ // left
            translateX = minX - layersWidth + padding;
        }
        if(translateY + padding > maxY){ // bottom
            translateY = maxY - padding;
        } else if(translateY + layersHeight - padding < minY){ // top
            translateY = minY - layersHeight + padding;
        }

        translate.setX(translateX);
        translate.setY(translateY);
    }


    @FXML
    private void handleNextAction() {
        // TODO execute and update database
        execManager.proceed();

        // update position and state of tokens
        List<Token> tokens = execManager.getTokens();
        tokenDisplayMap.keySet().removeIf(t -> !tokens.contains(t));
        executionLayer.getChildren().removeIf(c -> !tokenDisplayMap.values().contains(c));

        tokens.forEach(token -> {
            TokenShape tokenShape = tokenDisplayMap.get(token);
            Node node = entityDisplayMap.get(token.getCurrentEntity());
            if (tokenShape == null) {
                tokenShape = new TokenShape(node);
                tokenDisplayMap.put(token, tokenShape);
                executionLayer.getChildren().add(tokenShape);
            } else {
                tokenShape.setCurrentNode(node);
            }
        });
    }

    private void setLayerBindings() {
        layers.getTransforms().addAll(translate, zoomScale);
    }

    private void clipViewingRegion() {
        Rectangle clip = new Rectangle(0,0,0,0);
        clip.widthProperty().bind(contentPane.widthProperty());
        clip.heightProperty().bind(contentPane.heightProperty());
        contentPane.setClip(clip);
    }

    @FXML
    private void handleDrawAction() {
        fileManager.openFile();

        //only draw when the file is open
        if(fileManager.isFileOpen()){
            drawProgram();
        } else {
            //TODO could highlight File menu or focus ... as in twitter bootstrap tutorial
            messageData.sendMessage("No file opened yet. Use File->Open to select a program file.", MessageType.ERROR);
        }
    }

    private void resetFields(){
        zoomScale.setX(1);
        zoomScale.setY(1);
        translate.setX(0);
        translate.setY(0);
        entityDisplayMap.clear();
        tokenDisplayMap.clear();
        arrowsFrom.clear();
        arrowsTo.clear();
        diagramDrawn = false;
        diagramLayer.getChildren().clear();
        executionLayer.getChildren().clear();
        setDisplayMode(DisplayMode.VIEW);

        entityMap = fileManager.getRootMap();
        if(execManager == null){
            execManager = new ExecutionManager(entityMap);
            execManager.candidateActionsProperty().addListener((ListChangeListener.Change <? extends Entity> change) -> {
                while(change.next()){
                    if(change.wasAdded() && displayMode == DisplayMode.EXECUTION){
                        change.getAddedSubList().forEach(e -> setCandidate(e, true));
                    } else if(change.wasRemoved()) {
                        change.getRemoved().forEach(e -> setCandidate(e, false));
                    }
                }
            });

            execManager.toBeResolvedProperty().addListener((ListChangeListener.Change <? extends Entity> change) -> {
                while(change.next()){
                    if(change.wasAdded() && displayMode == DisplayMode.EXECUTION){
                        change.getAddedSubList().forEach(e -> highlight(entityDisplayMap.get(e), NodeState.RESOLVED, true));
                    } else if(change.wasRemoved()) {
                        change.getRemoved().forEach(e -> highlight(entityDisplayMap.get(e), NodeState.RESOLVED, false));
                    }
                }
            }); //TODO only show when mouse-over on a particular token

            // sync with selectedActionsProperty, so that when it is cleared, selected actions will be deselected
            execManager.selectedActionsProperty().addListener((ListChangeListener.Change<? extends Entity> change) -> {
                while(change.next()){
                    if(change.wasRemoved()) {
                        change.getRemoved().forEach(e -> setSelected(e, false));
                    }
                }
            });
        } else {
            execManager.reset(entityMap);
        }
    }

    private void setSelected(Entity e, boolean b) {
        ActionNode actionNode = (ActionNode) entityDisplayMap.get(e);
        if(actionNode != null){
            actionNode.setSelected(b);
        }
    }

    private void setCandidate(Entity e, boolean b) {
        ActionNode actionNode = (ActionNode) entityDisplayMap.get(e);
        if(actionNode != null){
            actionNode.setCandidate(b);
        }
    }

    private void highlight(Node node, NodeState state, boolean b) {
        PseudoClass pseudoClass = getPseudoClass(state);
        if (pseudoClass != null && node != null) {
            node.pseudoClassStateChanged(pseudoClass, b);
        }
    }

    private PseudoClass getPseudoClass(NodeState state) {
        PseudoClass pseudoClass;
        switch (state){
            case RESOLVED:
                pseudoClass = PseudoClass.getPseudoClass("resolved");
                break;
            default: //TODO remove cases
                pseudoClass = null;
                break;
        }
        return pseudoClass;
    }

    private void drawProgram() {
        resetFields();

        Group resultPane;
        double initX = 0;
        double initY = 0;

        //for each root entity, go through the chain and build the workflow diagram
        for(Entity rootEntity : entityMap.values()){
            resultPane = new Group();
            buildWorkflowDiagram(resultPane, rootEntity, initX, initY, true);
            diagramLayer.getChildren().add(resultPane);
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
            currNode = entityDisplayMap.get(currEntity);
            if(!currEntity.hasSingleChild()){
                // if current node is a multiChildEntity, next should be its 'nextEntities'.
                List<Entity> nextEntities = ((MultiChildEntity) currEntity).getNextEntities();
                nextX -= Constants.NODE_HORIZONTAL_GAP/2;

                // spread them out and draw each path
                for(Entity child : nextEntities){
                    buildWorkflowDiagram(resultGroup, child, nextX, nextY, true);
                    nextX = resultGroup.getLayoutBounds().getMaxX() + Constants.NODE_HORIZONTAL_GAP;
                    resultGroup.getChildren().add(createArrow(currNode, entityDisplayMap.get(child), true));
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
                    Node lastNode = entityDisplayMap.get(last);
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
                    nextX -= Constants.NODE_HORIZONTAL_GAP;
                    // spread them out and draw each path
                    //Draw True branch
                    buildWorkflowDiagram(resultGroup, trueNext, nextX, nextY, true);
                    nextX = resultGroup.getLayoutBounds().getMaxX();
                    resultGroup.getChildren().add(createArrow(currNode, entityDisplayMap.get(trueNext), true));
                    //Draw False branch
                    buildWorkflowDiagram(resultGroup, falseNext, nextX, nextY, true);
                    resultGroup.getChildren().add(createArrow(currNode, entityDisplayMap.get(falseNext), false));

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

    // Create a Node for given Entity, and store the mapping in entityDisplayMap.
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
            ((ActionNode)node).selectedProperty().addListener(observable -> {
                if(((BooleanProperty)observable).get()){
                    execManager.selectAction(entity, true);
                } else {
                    execManager.selectAction(entity, false);
                }
            });
        }
        node.setLayoutX(x);
        node.setLayoutY(y);
        entityDisplayMap.put(entity, node);
        return node;
    }
}
