package com.LPSWorkflow.LPS;

import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.execution.ExecAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for executing LPS programs
 */
public class ExecutionManager {
    private final Map<String, Entity> entityMap;
    private Database database;
    private List<ExecAgent> agents;

    // TODO now assuming there is one agent moving around. should allow multiple
    public ExecutionManager(Map<String, Entity> entityMap) {
        this.entityMap = entityMap;
        database = Database.getInstance();
        agents = new ArrayList<ExecAgent>();
    }

    public List<ExecAgent> getNextStep(){
        List<String> facts = Arrays.asList(database.getFacts().split(" "));
        List<ExecAgent> toBeRemoved = new ArrayList<ExecAgent>();

        // for each agent in the list, proceed to the next step
        for(ExecAgent agent : agents){
            Entity curr = agent.getCurrentEntity();
            if(holds(curr.getName(), facts)){
                agent.setCurrentEntity(curr.getNext());
            }

            // if next is null, remove from the list
            if(agent.getCurrentEntity() == null){
                toBeRemoved.add(agent);
            }
        }

        agents.removeAll(toBeRemoved);

        if(agents.isEmpty()){
            for(String root : entityMap.keySet()){
                if(holds(root, facts)){
                    agents.add(new ExecAgent(entityMap.get(root)));
                    break;
                }
            }
        }

        return agents;
        //TODO deal with multiChildNodes
    }

    private boolean holds(String root, List<String> facts) {
        //TODO include multiple conditions (:)
        return facts.contains(root) ;
    }

}
