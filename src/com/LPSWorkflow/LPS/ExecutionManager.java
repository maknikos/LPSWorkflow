package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Concurrent;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.execution.Token;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

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
    private int cycle;
    private List<Token> tokens;

    /* Candidate tokens property */
    private ListProperty<Token> candidateTokens = new SimpleListProperty<>(FXCollections.<Token>observableArrayList());
    public ListProperty<Token> candidateTokensProperty(){
        return candidateTokens;
    }

    public final List<Token> getCandidateTokens(){
        return candidateTokens.get();
    }

    public ExecutionManager(Map<String, Entity> entityMap) {
        // no need to initialise each time a file is opened, since a new one is created every time.
        cycle = 0;
        this.entityMap = entityMap;
        database = Database.getInstance();
        tokens = new ArrayList<>();

        spawnNewTokens();

        database.factsProperty().addListener((observableValue, oldStr, newStr) -> {
            updateCandidateTokens(Arrays.asList(newStr.split(" ")));
        });
    }

    private void updateCandidateTokens(List<String> facts) {
        getCandidateTokens().clear();
        getCandidateTokens().addAll(tokens.stream().filter(t -> isCandidate(t, facts)).collect(Collectors.toList()));
    }

    private boolean isCandidate(Token t, List<String> facts) {
        return facts.contains(t.getCurrentEntity().getName());
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        tokens.addAll(entityMap.values().stream().map(Token::new).collect(Collectors.toList()));
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

    public void proceed(){
        cycle++;
    }

    public int getCycle(){
        return cycle;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
