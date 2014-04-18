package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
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
    private Map<String, Entity> entityMap;
    private Database database;
    private int cycle;
    private List<Token> tokens;
    private List<String> facts;

    /* Candidate tokens property */
    private ListProperty<Token> candidateTokens = new SimpleListProperty<>(FXCollections.<Token>observableArrayList());
    public ListProperty<Token> candidateTokensProperty(){
        return candidateTokens;
    }

    public final List<Token> getCandidateTokens(){
        return candidateTokens.get();
    }

    public ExecutionManager(Map<String, Entity> entityMap) {
        cycle = 0;
        this.entityMap = entityMap;
        database = Database.getInstance();
        tokens = new ArrayList<>();
        facts = Arrays.asList(database.getFacts().split(" "));
        spawnNewTokens();

        database.factsProperty().addListener((observableValue, oldStr, newStr) -> {
            facts = Arrays.asList(newStr.split(" "));
            updateCandidateTokens();
        });
    }

    private void updateCandidateTokens() {
        getCandidateTokens().addAll(tokens.stream().filter(t -> isCandidate(t) && !getCandidateTokens().contains(t))
                .collect(Collectors.toList()));
        getCandidateTokens().removeIf(t -> !isCandidate(t));
    }

    private boolean isCandidate(Token t) {
        // all preconditions involving the token's entity must be satisfied. TODO

        Entity currentEntity = t.getCurrentEntity();
        if(currentEntity.getType() == EntityType.ACTION){

        }
        //TODO only actions are selected

        return holds(t.getCurrentEntity());
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        tokens.addAll(entityMap.values().stream().map(Token::new).collect(Collectors.toList()));
    }

    public void proceed(){
        tokens.forEach(this::tryResolve);
        tokens.removeIf(t -> t.getCurrentEntity() == null); // get rid of finished tokens
        tokens.forEach(Token::increment);
        updateCandidateTokens();
        cycle++;
    }

    private void tryResolve(Token t) {
        Entity currentEntity = t.getCurrentEntity();
        switch(currentEntity.getType()){
            case FLUENT:
                if(holds(currentEntity)){
                    t.setCurrentEntity(currentEntity.getNext());
                }
                break;
            case CONCURRENT:
            case ACTION:

            case AND:
            case OR:
            case PARTIAL_ORDER:

            case EXIT:
                break;
            default:
                break;
        }
    }

    private boolean holds(Entity currentEntity) {
        String name = currentEntity.getName();
        if(name.contains(":")){
            return facts.containsAll(Arrays.asList(name.split(":")));
        } else {
            return facts.contains(name);
        }
    }

    public int getCycle(){
        return cycle;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void reset(Map<String, Entity> entityMap){
        cycle = 0;
        this.entityMap = entityMap;
        tokens.clear();
        getCandidateTokens().clear();
        spawnNewTokens();
        updateCandidateTokens();
    }
}
