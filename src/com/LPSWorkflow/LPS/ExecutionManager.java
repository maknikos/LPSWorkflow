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
    private ListProperty<Entity> candidateEntities = new SimpleListProperty<>(FXCollections.<Entity>observableArrayList());
    public ListProperty<Entity> candidateEntitiesProperty(){
        return candidateEntities;
    }
    public final List<Entity> getCandidateEntities(){
        return candidateEntities.get();
    }


    /* ToBeResolved property : entities that will be resolved in current cycle */
    private ListProperty<Entity> toBeResolved = new SimpleListProperty<>(FXCollections.<Entity>observableArrayList());
    public ListProperty<Entity> toBeResolvedProperty(){
        return toBeResolved;
    }
    public final List<Entity> getToBeResolved(){
        return toBeResolved.get();
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
            updateToBeResolved();
        });
    }

    public void proceed(){
        tokens.forEach(this::tryResolve);
        tokens.removeIf(t -> t.getCurrentEntity() == null); // get rid of finished tokens
        tokens.forEach(Token::increment);
        updateCandidateTokens();
        updateToBeResolved();
        cycle++;
    }

    private void updateCandidateTokens() {
        getCandidateEntities().addAll(tokens.stream().map(Token::getCurrentEntity)
                .filter(e -> isCandidate(e) && !getCandidateEntities().contains(e)).collect(Collectors.toList()));
        getCandidateEntities().removeIf(e -> !isCandidate(e));
    }

    private void updateToBeResolved() {
        toBeResolved.clear();
        // check if current token's entities hold
        tokens.forEach(t -> {
            Entity current = t.getCurrentEntity();
            while (holds(current)) {
                getToBeResolved().add(current);
                current = current.getNext();
            }
        });
    }

    private boolean holds(Entity currentEntity) {
        if(currentEntity == null){
            return false;
        }
        String name = currentEntity.getName();
        if(name.contains(":")){
            return facts.containsAll(Arrays.asList(name.split(":")));
        } else {
            return facts.contains(name);
        }
    }

    private boolean isCandidate(Entity e) {
        // all preconditions involving the token's entity must be satisfied. TODO
        // must be associated with a token

        if(e.getType() == EntityType.ACTION){

        }
        //TODO only actions are selected

        return tokens.stream().anyMatch(t -> t.getCurrentEntity().equals(e)) && holds(e);
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        tokens.addAll(entityMap.values().stream().map(Token::new).collect(Collectors.toList()));
    }

    private void tryResolve(Token t) {
        Entity current = t.getCurrentEntity();
        switch(current.getType()){
            case FLUENT:
                while(holds(current)){
                    Entity next = current.getNext();
                    t.setCurrentEntity(next);
                    current = next;
                }
                break;
            case CONCURRENT:
            case ACTION:

            case AND:
            case OR:
            case PARTIAL_ORDER:

            case EXIT:
                break; //TODO
            default:
                break;
        }
    }

    public int getCycle(){
        return cycle;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void reset(Map<String, Entity> entityMap){
        this.entityMap = entityMap;
        clear();
        spawnNewTokens();
        updateCandidateTokens();
        updateToBeResolved();
    }

    public void clear(){
        cycle = 0;
        tokens.clear();
        toBeResolved.clear();
        candidateEntities.clear();
    }
}
