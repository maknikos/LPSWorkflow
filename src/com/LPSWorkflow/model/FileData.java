package com.LPSWorkflow.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model related to files
 */
public class FileData {
    private static FileData instance = null;

    public static FileData getInstance() {
        if (FileData.instance == null) {
            synchronized (FileData.class) {
                if (FileData.instance == null) {
                    FileData.instance = new FileData();
                }
            }
        }
        return instance;
    }

    private StringProperty filePath = new SimpleStringProperty(this, "str",
            "/Users/elijah6/Documents/Project/MainProject/LPSWorkflow/src/com/LPSWorkflow/antlr/LPSprogram");
    //TODO remove default path (or use xml config for default?)
    //TODO when doing so, ensure the path is not null (return "" at least)

    public StringProperty filePathProperty(){
        return filePath;
    }

    public final void setFilePath(String str){
        filePath.set(str);
    }

    public final String getFilePath(){
        return filePath.get();
    }
}
