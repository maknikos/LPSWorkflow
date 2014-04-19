package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.Fluent;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.execution.Token;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.*;
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
    private Map<Token, Entity> resolveMap; // stores the entity the token will point to in next cycle
    private Map<Token, List<Token>> tokenClones; // when branching out, clones are stored for each original token
    private List<Token> addedTokens;
    private List<Token> finishedTokens;

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
        resolveMap = new HashMap<>();
        tokenClones = new HashMap<>();
        addedTokens = new ArrayList<>();
        finishedTokens = new ArrayList<>();
        facts = Arrays.asList(database.getFacts().split(" "));
        spawnNewTokens();

        database.factsProperty().addListener((observableValue, oldStr, newStr) -> {
            facts = Arrays.asList(newStr.split(" "));
            updateCandidateTokens();
            updateToBeResolved();
        });
    }

    public void proceed(){
        tokens.addAll(addedTokens);
        tokens.removeAll(finishedTokens);
        tokenClones.values().forEach(tokens::addAll);
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
        resolveMap.clear();
        addedTokens.clear();
        finishedTokens.clear();

        // check if current token's entities hold
        tokens.forEach(t -> {
            updatePath(t, t.getCurrentEntity());
        });

        toBeResolved.addAll(resolveMap.values());
    }

    // pass through all resolvable entities for the token and record the last in the path.
    private void updatePath(Token t, Entity current) {
        while(current != null){
            resolveMap.put(t, current);
            switch(current.getType()){
                case FLUENT:
                    // for fluents, we need to take different paths depending on whether it holds or not
                    Fluent currentFluent = (Fluent) current;
                    if(holds(current) && current.getNext() != null){
                        current = current.getNext();
                    } else if (!holds(current) && currentFluent.getFalseNext() != null) {
                        current = currentFluent.getFalseNext();
                    } else {
                        current = null;
                    }
                    break;
                case CONCURRENT:
                    if(holds(current)){
                        current = current.getNext();
                    } else {
                        current = null;
                    }
                    break;
                case PARTIAL_ORDER:
                    // if clones are already made and t is waiting for them, check their status
                    if(tokenClones.containsKey(t) && tokens.containsAll(tokenClones.get(t))){
                        List<Token> clones = tokenClones.get(t);

                        //resolve clones first
                        clones.stream().filter(c -> !resolveMap.containsKey(c))
                                .forEach(c -> updatePath(c, c.getCurrentEntity()));
                        if(clones.stream().allMatch(c -> resolveMap.get(c).getType() == EntityType.EXIT)){
                            // remove all cloned tokens and proceed
                            finishedTokens.addAll(clones);
                            tokenClones.remove(t);
                            current = current.getNext();
                            break;
                        } else {
                            current = null;
                            break;
                        }
                    } else {
                        List<Token> clones = new ArrayList<>();
                        List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();
                        nextEntities.forEach(e -> {
                            Token clone = t.clone();
                            clones.add(clone);
                            updatePath(clone, e);
                        });

                        // if all path finished, proceed with the PartialOrder entity's Next
                        if(clones.stream().allMatch(nt -> resolveMap.get(nt).getType() == EntityType.EXIT)){
                            current = current.getNext();
                        } else {
                            tokenClones.put(t, clones);
                            current = null;
                        }
                        break;
                    }
                case AND:
                    // clone token for all paths and proceed through them
                    List<Entity> nextEntities = ((MultiChildEntity) current).getNextEntities();
                    nextEntities.forEach(e -> {
                        Token clone = t.clone();
                        addedTokens.add(clone);
                        updatePath(clone, e);
                    });
                    finishedTokens.add(t);
                    current = null;
                    break;
                case OR:
                    //TODO clone token ... if one path finishes, remove the other.
                case ACTION:
                    //TODO only if selected by user (later by strategy)
                case EXIT:
                    current = null;
                    break; //TODO change state of the token to indicate finish state?
                default:
                    break;
            }
        }
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

    private void tryResolve(Token token) { //TODO consider goalDef
        token.setCurrentEntity(resolveMap.get(token));
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
        resolveMap.clear();
        tokenClones.clear();
        finishedTokens.clear();
        addedTokens.clear();
    }
}
