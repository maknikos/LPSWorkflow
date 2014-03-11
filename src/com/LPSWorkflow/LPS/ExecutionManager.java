package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.database.Database;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for executing LPS programs
 */
public class ExecutionManager {
    private final Map<String, Entity> entityMap;
    private Entity currentStep;
    private Database database;

    // TODO now assuming there is one agent moving around. should allow multiple
    public ExecutionManager(Map<String, Entity> entityMap) {
        this.entityMap = entityMap;
        database = Database.getInstance();
    }

    public Entity getNextEntity(){
        List<String> facts = Arrays.asList(database.getFacts().split(" "));
        if(currentStep == null){
            for(String root : entityMap.keySet()){
                if(holds(root, facts)){
                    currentStep = entityMap.get(root);
                    break;
                }
            }
        } else {
            currentStep = currentStep.getNext();
        }
        return currentStep;



        //TODO deal with multiChildNodes
    }

    private boolean holds(String root, List<String> facts) {
        //TODO include multiple conditions (:)
        return facts.contains(root) ;
    }

}
