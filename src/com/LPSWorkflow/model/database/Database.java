package com.LPSWorkflow.model.database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Database that holds the current state of the program (fluents/conditions...)
 * Database holds Facts (can change) and Rules (holds for the program).
 */
public class Database {
    private static Database instance = null;
    //TODO private List<String> facts;
    private List<Rule> rules;

    private Database() {
        //TODO this.facts = new ArrayList<String>();
        this.rules = new ArrayList<Rule>();
    }

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

//    TODO
//    public List<Rule> getRules() {
//        return rules;
//    }
//
//    public void setRules(List<Rule> rules) {
//        this.rules = rules;
//    }
}
