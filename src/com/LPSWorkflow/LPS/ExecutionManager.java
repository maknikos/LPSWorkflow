package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Concurrent;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.execution.ExecAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class responsible for executing LPS programs
 */
public class ExecutionManager {
    private final Map<String, Entity> entityMap;
    private Database database;
    private List<ExecAgent> agents;
    private LPSFileManager fileManager;

    public ExecutionManager(Map<String, Entity> entityMap) {
        this.entityMap = entityMap;
        database = Database.getInstance();
        agents = new ArrayList<>();
        fileManager = LPSFileManager.getInstance();

        // reset agent list if file re-opened
        fileManager.isFileOpenProperty().addListener(observable -> agents.clear());
    }

    public List<ExecAgent> getNextStep(){
        List<String> facts = Arrays.asList(database.getFacts().split(" "));
        List<ExecAgent> toBeRemoved = new ArrayList<>();

        // for each agent in the list, proceed to the next step
        for(ExecAgent agent : agents){
            Entity curr = agent.getCurrentEntity();
            if(holds(curr, facts)){
                agent.setCurrentEntity(curr.getNext());
                agent.increment();
            }

            // if next is null, remove from the list
            if(agent.getCurrentEntity() == null){
                toBeRemoved.add(agent);
            }
        }

        agents.removeAll(toBeRemoved);

        if(agents.isEmpty()){
            for(Entity root : entityMap.values()){
                if(holds(root, facts)){
                    agents.add(new ExecAgent(entityMap.get(root)));
                    break;
                }
            }
        }

        return agents;
        //TODO deal with multiChildNodes
    }

    private boolean holds(Entity root, List<String> facts) {
        //TODO deal with other types of entities, too

        EntityType type = root.getType();
        switch (type){
            case ACTION:
            case FLUENT:
                return facts.contains(root.getName());
            case CONCURRENT:
                List<Entity> entities = ((Concurrent) root).getEntities();
                List<String> names = entities.stream().map(Entity::getName).collect(Collectors.toList());
                return facts.containsAll(names);
        }

        return false;
    }

}
