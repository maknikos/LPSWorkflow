package com.LPSWorkflow.LPS;

import com.LPSWorkflow.antlr.LPSLexer;
import com.LPSWorkflow.antlr.LPSLoader;
import com.LPSWorkflow.antlr.LPSParser;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Deals with LPS files
 */
public class LPSFileManager {
    private Map<String,Entity> rootMap;
    private List<String> fluents;
    private List<List<Entity>> preconditions;
    private MessageData messageData;
    private List<Postcondition> postconditions;
    private static LPSFileManager instance = null;

    public final static LPSFileManager getInstance() {
        if (LPSFileManager.instance == null) {
            synchronized (LPSFileManager.class) {
                if (LPSFileManager.instance == null) {
                    LPSFileManager.instance = new LPSFileManager();
                }
            }
        }
        return instance;
    }

    private LPSFileManager() {
        setIsFileOpen(false);
        messageData = MessageData.getInstance();
    }

    public Map<String, Entity> getRootMap() {
        return rootMap;
    }

    /**
     * Opens and parses the LPS program files.
     * @param fileData The path of the file.
     */
    public void openFile(String fileData) {
        setIsFileOpen(false);
        messageData.getMessageList().clear(); // reset the message list
        InputStream is;
        ANTLRInputStream input = null;
        try {
            if (fileData != null) {
                is = new FileInputStream(fileData);
            } else {
                return;
            }
            input = new ANTLRInputStream(is);
        } catch (IOException e) {
            messageData.sendMessage(e.getMessage(), MessageType.ERROR);
            e.printStackTrace();
        }
        if(input == null) {
            messageData.sendMessage("Failed to read file '" + fileData + "'.", MessageType.ERROR);
            return;
        }

        try{
            LPSLexer lexer = new LPSLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LPSParser parser = new LPSParser(tokens);
            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            LPSLoader loader = new LPSLoader();
            walker.walk(loader, tree);

            if(parser.getErrorHandler().inErrorRecoveryMode(parser)){
                messageData.sendMessage("Failed to read file. Syntax error occurred.", MessageType.ERROR);
                return;
            }
            StructureBuilder structureBuilder = new StructureBuilder();
            structureBuilder.build(loader.getReactiveRuleRoots(), loader.getReactiveRuleConnections(),
                    loader.getGoalRoots(), loader.getGoalConnections(), loader.getFluents());
            this.rootMap = structureBuilder.getReactiveRulesRootMap();
            this.fluents = loader.getFluents();
            this.preconditions = loader.getPreconditions();
            this.postconditions= loader.getPostconditions();
            setIsFileOpen(true);
        } catch(Exception e){
            messageData.sendMessage("Failed to read file: " + e.toString(), MessageType.ERROR);
            e.printStackTrace();
        }

    }

    public List<String> getFluents() {
        return fluents;
    }

    public List<List<Entity>> getPreconditions() {
        return preconditions;
    }

    public List<Postcondition> getPostconditions() {
        return postconditions;
    }

    public boolean isFileOpen(){
        return isFileOpen.get();
    }

    private BooleanProperty isFileOpen = new SimpleBooleanProperty();
    public BooleanProperty isFileOpenProperty(){
        return isFileOpen;
    }
    public final void setIsFileOpen(boolean isFileOpen){
        this.isFileOpen.set(isFileOpen);
    }
    public final boolean getIsFileOpen(){
        return isFileOpen.get();
    }
}
