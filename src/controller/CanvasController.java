package controller;

import model.FileData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.JLPS;
import model.*;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Controller for the main canvas
 */
public class CanvasController implements Initializable {
    private FileData fileData;

    @FXML
    private Label filePathLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileData = FileData.getInstance();
        filePathLabel.textProperty().bind(fileData.filePathProperty());
    }

    public void handleTextChange() {
        openFile();

        //Database db = Database.getInstance();
        ReactiveRuleSet rr = ReactiveRuleSet.getInstance();
        //CycleHandler ch = CycleHandler.getInstance();
        //GoalsList gl = GoalsList.getInstance();

        Field f;
        ArrayList<ReactiveRule> rules = null;

        try {
            // Since reactiveRules field is private in JLPS library, we use reflection to access it.
            f = rr.getClass().getDeclaredField("reactiveRules");
            if (f != null) {
                f.setAccessible(true);
                rules = (ArrayList<ReactiveRule>) f.get(rr);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(rules == null){
            return; //TODO handle fail case
        }

        drawReactiveRules(rules);
    }

    private void drawReactiveRules(ArrayList<ReactiveRule> rules) {
        fileData.setFilePath(rules.get(0).toString());
    }

    private void openFile() {
        // Open and parse the LPS program file
        HashSet<String> facts = new HashSet<String>();
        HashSet<String> actions  = new HashSet<String>();
        try {
            JLPS.fileReader(JLPS.fileOpener(fileData.getFilePath(), true), facts, actions);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (RecognitionException e1) {
            e1.printStackTrace();
        }
    }
}
