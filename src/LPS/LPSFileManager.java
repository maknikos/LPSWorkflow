package LPS;

import main.JLPS;
import model.ReactiveRule;
import model.ReactiveRuleSet;
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
        getReactiveRules();
    }

    /**
     *
     * @return Returns ArrayList of reactive rules.
     */
    public ArrayList getReactiveRules() {
        //Database db = Database.getInstance();
        //CycleHandler ch = CycleHandler.getInstance();
        //GoalsList gl = GoalsList.getInstance();

        ReactiveRuleSet rr = ReactiveRuleSet.getInstance();
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
        return rules;
    }
}
