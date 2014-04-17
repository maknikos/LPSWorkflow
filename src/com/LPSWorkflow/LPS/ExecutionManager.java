package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Concurrent;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.execution.GreedyStrategy;
import com.LPSWorkflow.model.execution.Strategy;
import com.LPSWorkflow.model.execution.Token;

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
    private List<Token> agents;
    private Strategy strategy;
    private int cycle;

    public ExecutionManager(Map<String, Entity> entityMap) {
        // no need to initialise each time a file is opened, since a new one is created every time.
        cycle = 0;
        this.entityMap = entityMap;
        database = Database.getInstance();
        agents = new ArrayList<>();
        strategy = new GreedyStrategy(); //TODO make it interchangeable
    }

    public List<Token> getNextStep(){
        List<String> facts = getFactStrings();

        // for each agent in the list, proceed to the next step
        for(Token agent : agents){
            Entity curr = agent.getCurrentEntity();
            if(holds(curr, facts)){
                agent.setCurrentEntity(curr.getNext());
                agent.increment();
            }
        }

        // if next is null, remove from the list
        agents = agents.stream().filter(a -> a.getCurrentEntity() != null).collect(Collectors.toList());

        spawnNewTokens();

        return this.agents;
    }

    private List<String> getFactStrings() {
        return Arrays.asList(database.getFacts().split(" "));
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        for(Entity root : entityMap.values()){
            agents.add(new Token(root));
        }
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
