package LPS;

import main.JLPS;
import model.ReactiveRuleSet;
import model.SimpleSentence;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Deals with LPS files
 */
public class LPSFileManager {

    /**
     * Opens and parses the LPS program files.
     * @param fileData The path of the file.
     */
    public void openFile(String fileData) {
        // Open and parse the LPS program file
        HashSet<String> facts = new HashSet<String>();
        HashSet<String> actions  = new HashSet<String>();
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
        //GoalsList gl = GoalsList.getInstance();

        return (ArrayList) getHiddenField(ReactiveRuleSet.getInstance(), "reactiveRules");
    }

    public String getCause(Object obj) {
        SimpleSentence s = (SimpleSentence) getHiddenField(obj, "causes");

        if(s == null)
            return null;

        return s.getName();
    }

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
}
