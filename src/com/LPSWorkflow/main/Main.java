package com.LPSWorkflow.main;

import com.LPSWorkflow.model.FileData;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        FileData fileData = FileData.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));

        String filePath = fileData.getFilePath();
        if(filePath != null && !filePath.isEmpty()) {
            primaryStage.setTitle("LPS Workflow - [" + fileData.getFilePath() + "]");
        } else {
            primaryStage.setTitle("LPS Workflow");
        }

        fileData.filePathProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldString, String newString) {
                primaryStage.setTitle("LPS Workflow - [" + newString + "]");
            }
        });

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add("com/LPSWorkflow/styles/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
