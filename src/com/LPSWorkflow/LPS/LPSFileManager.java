package com.LPSWorkflow.LPS;

import com.LPSWorkflow.antlr.LPSLexer;
import com.LPSWorkflow.antlr.LPSLoader;
import com.LPSWorkflow.antlr.LPSParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Deals with LPS files
 */
public class LPSFileManager {
    private boolean isFileOpen;

    public LPSFileManager() {
        isFileOpen = false;
    }

    /**
     * Opens and parses the LPS program files.
     * @param fileData The path of the file.
     */
    public void openFile(String fileData) {
        InputStream is = System.in;
        ANTLRInputStream input = null;
        try {
            if ( fileData!=null ) is = new FileInputStream(fileData);
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
        structureBuilder.build(loader.getReactiveRuleConnections(), loader.getGoalConnections());

        System.out.println(tree.toStringTree(parser));

    }

    public boolean isFileOpen(){
        return isFileOpen;
    }
}
