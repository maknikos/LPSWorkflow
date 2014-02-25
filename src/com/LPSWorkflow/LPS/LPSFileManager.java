package com.LPSWorkflow.LPS;

import com.LPSWorkflow.antlr.LPSLexer;
import com.LPSWorkflow.antlr.LPSLoader;
import com.LPSWorkflow.antlr.LPSParser;
import com.LPSWorkflow.model.abstractComponent.Entity;
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
    private boolean isFileOpen;
    private Map<String,Entity> rootMap;
    private List<String> fluents;

    public LPSFileManager() {
        isFileOpen = false;
    }

    public Map<String, Entity> getRootMap() {
        return rootMap;
    }

    /**
     * Opens and parses the LPS program files.
     * @param fileData The path of the file.
     */
    public void openFile(String fileData) {
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
            e.printStackTrace();
        }
        if(input == null){
            return; //TODO error handling?
        }

        LPSLexer lexer = new LPSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LPSParser parser = new LPSParser(tokens);
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        LPSLoader loader = new LPSLoader();
        walker.walk(loader, tree);

        StructureBuilder structureBuilder = new StructureBuilder();
        structureBuilder.build(loader.getReactiveRuleRoots(), loader.getReactiveRuleConnections(),
                loader.getGoalRoots(), loader.getGoalConnections());
        this.rootMap = structureBuilder.getReactiveRulesRootMap();
        this.fluents = loader.getFluents();
        this.isFileOpen = true;
    }

    public List<String> getFluents() {
        return fluents;
    }

    public boolean isFileOpen(){
        return isFileOpen;
    }
}
