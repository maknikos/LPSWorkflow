package controller;

import model.FileData;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the top menu bar
 */
public class MenuController implements Initializable{
    private final FileChooser fileChooser = new FileChooser();
    private FileData fileData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
    }

    /**
     * Opens a file browse dialog
     */
    public void handleOpen() {
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            openFile(file);
        }
    }

    /**
     * Quits the application
     */
    public void handleQuit() {
        Platform.exit();
    }

    private void openFile(File file) {
        fileData.setFilePath(file.getAbsolutePath());
    }
}
