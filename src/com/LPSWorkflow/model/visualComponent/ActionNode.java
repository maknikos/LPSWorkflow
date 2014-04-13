package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private VBox vBox;
    private Button expandButton;
    private Group goalDefinition;
    private boolean isExpanded;

    public ActionNode(String name, final Group goalDefinition) {
        super(name);
        this.getStyleClass().add("action-node");

        vBox = new VBox();
        text.setMinWidth(width);
        text.setMaxWidth(Double.MAX_VALUE);
        text.getStyleClass().add("action-node-label");
        vBox.getChildren().add(text);
        getChildren().add(vBox);

        if (goalDefinition != null) {
            this.goalDefinition = goalDefinition;
            expandButton = new Button("+");
            StackPane.setAlignment(expandButton, Pos.TOP_LEFT);
            initExpandButton();
            getChildren().add(expandButton);
            setExpanded(false);
        }
    }

    private void initExpandButton() {
        // When clicked on expand button, it will show goal definitions ("expand")
        expandButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private Map<javafx.scene.Node, Double> nodesPushed = new HashMap<javafx.scene.Node, Double>();

            @Override
            public void handle(MouseEvent mouseEvent) {



                if(mouseEvent.isShiftDown()){
                    //TODO open in a new window/panel
                    Stage stage = new Stage();
                    stage.setTitle(getName());

                    final Group goalDefClone = new Group(goalDefinition);
                    Scene scene = new Scene(goalDefClone, 600, 600);
                    // move view point using scroll
                    scene.setOnScroll(new EventHandler<ScrollEvent>() {
                        @Override
                        public void handle(ScrollEvent e) {
                            double translateX = e.getDeltaX();
                            double translateY = e.getDeltaY();
                            goalDefClone.setTranslateX(goalDefClone.getTranslateX() + translateX);
                            goalDefClone.setTranslateY(goalDefClone.getTranslateY() + translateY);
                            goalDefClone.setTranslateX(goalDefClone.getTranslateX() + translateX);
                            goalDefClone.setTranslateY(goalDefClone.getTranslateY() + translateY);
                        }
                    });
                    stage.setScene(scene);
                    stage.show();




                } else {
                    ActionNode source = (ActionNode) ((Button) mouseEvent.getSource()).getParent();
                    boolean isExpanded = !source.isExpanded();

                    Bounds prevSourceBounds = source.getBoundsInParent();
                    source.setExpanded(isExpanded);
                    if (isExpanded) {
                        pushOutNodes(prevSourceBounds, source);
                    } else {
                        revertNodes();
                    }
                }
            }

            private void pushOutNodes(Bounds prevSourceBounds, ActionNode source) {
                Group parentGroup = (Group) getParent();
                Bounds sourceBounds = source.getBoundsInParent();

                // check if it intersects with any nodes
                boolean intersects = false;
                List<Node> relevantChildren = new ArrayList<Node>();
                for (javafx.scene.Node node : parentGroup.getChildren()) {
                    Bounds nodeBounds = node.getBoundsInParent();
                    // should only include Nodes, excluding itself
                    // also, only the ones not below this expanding node, and to the right.
                    if (node instanceof Node && !source.equals(node)
                            && prevSourceBounds.getMinX() < nodeBounds.getMinX()
                            && sourceBounds.getMaxY() > nodeBounds.getMinY()) {
                        relevantChildren.add((Node) node);
                        intersects = intersects || sourceBounds.intersects(node.getBoundsInParent());
                    }
                }

                if (intersects) {
                    for (Node node : relevantChildren) {
                        // push sideways
                        double prevX = node.getLayoutX();
                        double moveX = sourceBounds.getWidth() - prevSourceBounds.getWidth();
                        nodesPushed.put(node, moveX);
                        node.setLayoutX(prevX + moveX);
                    }
                }
            }

            private void revertNodes() {
                // revert to previous positions
                for (javafx.scene.Node node : nodesPushed.keySet()) {
                    node.setLayoutX(node.getLayoutX() - nodesPushed.get(node));
                }
                nodesPushed.clear();
            }
        });
    }

    private void setExpanded(boolean b) {
        isExpanded = b;

        if(b){
            vBox.getChildren().add(goalDefinition);
            text.pseudoClassStateChanged(PseudoClass.getPseudoClass("expanded"), true);
            text.setPrefHeight(22);
            expandButton.setText("-");
        } else {
            vBox.getChildren().remove(goalDefinition);
            text.pseudoClassStateChanged(PseudoClass.getPseudoClass("expanded"), false);
            text.setPrefSize(width, height);
            expandButton.setText("+");
        }
    }

    private boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public EntityType getType() {
        return EntityType.ACTION;
    }
}
