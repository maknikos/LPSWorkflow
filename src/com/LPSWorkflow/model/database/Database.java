package com.LPSWorkflow.model.database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Database that holds the current state of the program (fluents/conditions...)
 * Database holds Facts (can change) and Rules (holds for the program).
 */
public class Database {
    private static Database instance = null;

    public final static Database getInstance() {
        if (Database.instance == null) {
            synchronized (Database.class) {
                if (Database.instance == null) {
                    Database.instance = new Database();
                }
            }
        }
        return instance;
    }

    private StringProperty facts = new SimpleStringProperty(this, "str", "");
    public StringProperty factsProperty(){
        return facts;
    }
    public final void setFacts(String str){
        facts.set(str);
    }
    public final String getFacts(){
        return facts.get();
    }
}
