package com.LPSWorkflow.model.visualComponent;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private VBox vBox;
    private Label text;
    private Button expandButton;

    private Group goalDefinition;
    private boolean isExpanded;

    public ActionNode(String name, Group goalDefinition) {
        super(name);

        if(goalDefinition != null){
            goalDefinition.setStyle("-fx-border-color:black;");
            this.goalDefinition = goalDefinition;
        } else {
            this.goalDefinition = new Group();
        }
         //TODO cleanup the structure of the ActionNode
        vBox = new VBox();
        vBox.setStyle("-fx-border-color:black; -fx-border-radius:5px");
        vBox.setAlignment(Pos.CENTER);

        expandButton = new Button("+");
        expandButton.setVisible(hasGoalDefinition());
        text = new Label(name);
        text.setAlignment(Pos.CENTER);
        text.setPrefSize(width, height);
        text.setMinWidth(width);

        vBox.getChildren().add(text);
        getChildren().addAll(vBox, expandButton);
        setExpanded(false);

        // When clicked on expand button, it will show goal definitions ("expand")
        expandButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private Map<javafx.scene.Node, Double> nodesPushed = new HashMap<javafx.scene.Node, Double>();

            @Override
            public void handle(MouseEvent mouseEvent) {

                if(mouseEvent.isShiftDown()){

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

        //TODO there may be a better way to show the label
        if(b){
            vBox.getChildren().add(goalDefinition);
            text.setStyle("-fx-font-size:18px; -fx-border-color:black; -fx-border-width:0 0 1 0");
            text.prefWidthProperty().bind(vBox.widthProperty()); // TODO mouseover on a node causes the whole group to grow bit by bit...
            text.setPrefHeight(22);
            expandButton.setText("-");
        } else {
            vBox.getChildren().remove(goalDefinition);
            text.setStyle("-fx-font-size:25px; -fx-border-color:transparent;");
            text.prefWidthProperty().unbind();
            text.setPrefSize(width, height);
            expandButton.setText("+");
        }
    }

    private boolean isExpanded() {
        return isExpanded;
    }

    public boolean hasGoalDefinition(){
        return goalDefinition != null
                && goalDefinition.getChildren().size() > 0;
    }
}
