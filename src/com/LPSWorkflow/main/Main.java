package com.LPSWorkflow.main;

import com.LPSWorkflow.model.FileData;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        FileData fileData = FileData.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));

        SimpleStringProperty prefix = new SimpleStringProperty("LPS Workflow - [");
        SimpleStringProperty suffix = new SimpleStringProperty("]");
        primaryStage.titleProperty().bind(prefix.concat(fileData.filePathProperty()).concat(suffix));
        primaryStage.getIcons().add(new Image("com/LPSWorkflow/styles/logo.png"));
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add("com/LPSWorkflow/styles/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
