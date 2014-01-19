package com.LPSWorkflow.LPS;

import main.JLPS;
import model.*;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

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
    }

    /**
     *
     * @return Returns ArrayList of reactive rules.
     */
    public ArrayList getReactiveRules() {
        //Database db = Database.getInstance(); TODO cleanup
        //CycleHandler ch = CycleHandler.getInstance();
        GoalsList gl = GoalsList.getInstance();
        GoalSet goals = gl.getGoalsDefinitions(); //TODO move to different method


        return (ArrayList) getHiddenField(ReactiveRuleSet.getInstance(), "reactiveRules");
    }

    /**
     * From ReactiveRule object, get the cause (antecedent) part.
     * @param obj ReactiveRule object
     * @return cause part of the ReactiveRule
     */
    public String getCause(Object obj) {
        SimpleSentence s = (SimpleSentence) getHiddenField(obj, "causes");

        if(s == null)
            return null;

        return s.getName();
    }

    /**
     * From ReactiveRule object, get the goal (consequent) part.
     * @param obj ReactiveRule object
     * @return goal part of the ReactiveRule
     */
    public String getGoal(Object obj) {
        SimpleSentence s = (SimpleSentence) getHiddenField(obj, "goal");

        if(s == null)
            return null;

        return s.getName();
    }

    private Object getHiddenField(Object obj, String fieldName) {
        Field f;
        Object result = null;
        try {
            // We use reflection to access hidden variables in JLPS library
            f = obj.getClass().getDeclaredField(fieldName);
            if (f != null) {
                f.setAccessible(true);
                result = f.get(obj);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void clearPreviousData() {
        // used to clear all data from previous loaded file
        //TODO add any other singleton used ...
        ((ArrayList) getHiddenField(ReactiveRuleSet.getInstance(), "reactiveRules")).clear();
    }
}
