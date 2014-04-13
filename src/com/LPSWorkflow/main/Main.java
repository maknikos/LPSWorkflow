package com.LPSWorkflow.main;

import com.LPSWorkflow.model.FileData;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        FileData fileData = FileData.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));

        SimpleStringProperty prefix = new SimpleStringProperty("LPS Workflow - [");
        SimpleStringProperty suffix = new SimpleStringProperty("]");
        primaryStage.titleProperty().bind(prefix.concat(fileData.filePathProperty()).concat(suffix));

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add("com/LPSWorkflow/styles/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
