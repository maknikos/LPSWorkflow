package com.LPSWorkflow.model.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Database that holds the current state of the program (fluents/conditions...)
 * Database holds Facts (can change) and Rules (holds for the program).
 */
public class Database {
    private static Database instance = null;
    private List<String> facts;
    private List<Rule> rules;

    private Database() {
        this.facts = new ArrayList<String>();
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

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
