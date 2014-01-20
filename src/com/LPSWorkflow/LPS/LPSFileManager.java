package com.LPSWorkflow.LPS;

import main.JLPS;
import model.ReactiveRuleSet;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static com.LPSWorkflow.common.ReflectionHelper.getHiddenField;

/**
 * Deals with LPS files
 */
public class LPSFileManager {
    HashSet<String> facts;
    HashSet<String> actions;


    /**
     * Opens and parses the LPS program files.
     * @param fileData The path of the file.
     */
    public void openFile(String fileData) {

        //Reset data TODO cleanup
        clearPreviousData();

        // Open and parse the LPS program file
        facts = new HashSet<String>();
        actions = new HashSet<String>();
        try {
            JLPS.fileReader(JLPS.fileOpener(fileData, true), facts, actions);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (RecognitionException e1) {
            e1.printStackTrace();
        }


        //Database db = Database.getInstance(); TODO cleanup
        //CycleHandler ch = CycleHandler.getInstance();

    }

    private void clearPreviousData() {
        // used to clear all data from previous loaded file
        //TODO add any other singleton used ...
        ((ArrayList) getHiddenField(ReactiveRuleSet.getInstance(), "reactiveRules")).clear();
    }
}
