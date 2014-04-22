package com.LPSWorkflow.model.visualComponent;

import com.LPSWorkflow.common.EntityType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Visual component representing an action
 */
public class ActionNode extends Node {
    private VBox vBox;
    private Button expandButton;
    private CheckBox checkBox;
    private Group goalDefinition;
    private boolean isExpanded;

    /* selected property */
    private BooleanProperty selected = new SimpleBooleanProperty(false);
    public boolean getSelected() {
        return selected.get();
    }
    public BooleanProperty selectedProperty() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    /* available property */
    private BooleanProperty available = new SimpleBooleanProperty(false);
    public boolean getAvailable() {
        return available.get();
    }
    public BooleanProperty availableProperty() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available.set(available);
    }


    public ActionNode(String name, final Group goalDefinition) {
        super(name);
        this.getStyleClass().add("action-node");
        vBox = new VBox();
        text.setMaxWidth(Double.MAX_VALUE);
        text.getStyleClass().add("action-node-label");
        vBox.getChildren().add(text);
        checkBox = new CheckBox();
        checkBox.getStyleClass().add("action-node-check-box");
        StackPane.setAlignment(checkBox, Pos.BOTTOM_RIGHT);
        getChildren().addAll(vBox, checkBox);

        selected.bindBidirectional(checkBox.selectedProperty());
        checkBox.visibleProperty().bind(selected.or(available));

        // only add expand button if it has a goalDefinition
        if (goalDefinition != null && goalDefinition.getChildren().size() > 0) {
            this.goalDefinition = goalDefinition;
            VBox.setMargin(this.goalDefinition, new Insets(0, 10, 10, 10));
            expandButton = createExpandButton();
            getChildren().add(expandButton);
            setExpanded(false);
        }
    }

    private Button createExpandButton() {
        Button button = new Button("+");
        StackPane.setAlignment(button, Pos.TOP_LEFT);

        // When clicked on expand button, it will show goal definitions ("expand")
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private Map<Node, Double> nodesPushed = new HashMap<>();

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isShiftDown()) {
                    openInNewWindow();
                } else {
                    ActionNode source = (ActionNode) ((Button) mouseEvent.getSource()).getParent();
                    Bounds prevSourceBounds = source.getBoundsInParent();
                    boolean expand = !source.isExpanded();
                    source.setExpanded(expand);
                    if (expand) {
                        // get all other Nodes in the canvas
                        Group parentGroup = (Group) getParent();
                        List<Node> nodes = parentGroup.getChildren().stream()
                                .filter(c -> c instanceof Node && !c.equals(source))
                                .map(c -> (Node) c)
                                .collect(Collectors.toList());
                        pushOutNodes(nodes, prevSourceBounds, source);
                    } else {
                        revertNodes();
                    }
                }
            }

            private void pushOutNodes(List<Node> nodes, Bounds prevSourceBounds, ActionNode source) {
                Bounds sourceBounds = source.getBoundsInParent();

                // check if it intersects with any nodes
                List<Node> affectedNodes  = nodes.stream()
                        .filter(node -> prevSourceBounds.getMaxX() < node.getBoundsInParent().getMinX())
                        .collect(Collectors.toList());
                boolean intersects = affectedNodes.stream()
                        .anyMatch(node -> sourceBounds.intersects(node.getBoundsInParent()));

                // push out only if something intersects
                if (intersects) {
                    affectedNodes.forEach(node -> {
                        // push sideways
                        double prevX = node.getLayoutX();
                        double moveX = sourceBounds.getWidth() - prevSourceBounds.getWidth();
                        nodesPushed.put(node, moveX);
                        node.setLayoutX(prevX + moveX);
                    });
                }
            }

            private void revertNodes() {
                // revert to previous positions
                nodesPushed.forEach((node, value) -> node.setLayoutX(node.getLayoutX() - value));
                nodesPushed.clear();
            }

            private void openInNewWindow() {
                // TODO open in new window ...
                Stage stage = new Stage();
                stage.setTitle(getName());

                final Group goalDefClone = new Group(goalDefinition);
                Scene scene = new Scene(goalDefClone, 600, 600);
                // move view point using scroll
                scene.setOnScroll((ScrollEvent e) -> {
                    double translateX = e.getDeltaX();
                    double translateY = e.getDeltaY();
                    goalDefClone.setTranslateX(goalDefClone.getTranslateX() + translateX);
                    goalDefClone.setTranslateY(goalDefClone.getTranslateY() + translateY);
                    goalDefClone.setTranslateX(goalDefClone.getTranslateX() + translateX);
                    goalDefClone.setTranslateY(goalDefClone.getTranslateY() + translateY);
                });
                stage.setScene(scene);
                stage.show();
            }
        });
        return button;
    }

    private void setExpanded(boolean b) {
        isExpanded = b;
        if(b){
            vBox.getChildren().add(goalDefinition);
            text.pseudoClassStateChanged(PseudoClass.getPseudoClass("expanded"), true);
            expandButton.setText("-");
        } else {
            vBox.getChildren().remove(goalDefinition);
            text.pseudoClassStateChanged(PseudoClass.getPseudoClass("expanded"), false);
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
