package com.LPSWorkflow.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model related to files
 */
public class FileData {
    private static FileData instance = null;

    public final static FileData getInstance() {
        if (FileData.instance == null) {
            synchronized (FileData.class) {
                if (FileData.instance == null) {
                    FileData.instance = new FileData();
                }
            }
        }

        return instance;
    }

    private StringProperty filePath = new SimpleStringProperty(this, "str", "Default");

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
