package com.LPSWorkflow.model.domainTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * Precondition containing conflicting entities
 */
public class Precondition {
    List<String> conflictingNames;

    public Precondition(List<String> entities) {
        conflictingNames = entities;
    }

    public List<String> getConflictingNames() {
        return conflictingNames;
    }

    public List<String> getConflictingNamesExcept(String... names){
        List<String> result = new ArrayList<>(conflictingNames);
        for(String name : names){
            result.remove(name);
        }
        return result;
    }

    @Override
    public String toString(){
        String conjunction = "";
        for (String name : getConflictingNames()) {
            conjunction = conjunction.concat(name + " & ");
        }
        conjunction = conjunction.substring(0, conjunction.length() - 2); // remove trailing &
        return String.format("false <- %s", conjunction);
    }
}
