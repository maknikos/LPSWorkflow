package com.LPSWorkflow.LPS;

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

        spawnNewTokens();

        database.factsProperty().addListener((observableValue, oldStr, newStr) -> {
            updateCandidateTokens(Arrays.asList(newStr.split(" ")));
        });
    }

    private void updateCandidateTokens(List<String> facts) {
        getCandidateTokens().addAll(tokens.stream().filter(t -> isCandidate(t, facts) && !getCandidateTokens().contains(t))
                .collect(Collectors.toList()));
        getCandidateTokens().removeIf(t -> !isCandidate(t, facts));
    }

    private boolean isCandidate(Token t, List<String> facts) {
        return facts.contains(t.getCurrentEntity().getName());
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        tokens.addAll(entityMap.values().stream().map(Token::new).collect(Collectors.toList()));
    }

    public void proceed(){
        tokens.forEach(Token::increment);
        cycle++;
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
        tokens = new ArrayList<>();

        spawnNewTokens();
    }
}
