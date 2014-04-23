package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.EntityType;
import com.LPSWorkflow.model.abstractComponent.Entity;
import com.LPSWorkflow.model.abstractComponent.Fluent;
import com.LPSWorkflow.model.abstractComponent.MultiChildEntity;
import com.LPSWorkflow.model.database.Database;
import com.LPSWorkflow.model.domainTheory.DomainTheoryData;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import com.LPSWorkflow.model.domainTheory.Precondition;
import com.LPSWorkflow.model.execution.Token;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class responsible for executing LPS programs
 */
public class ExecutionManager {
    private static final String factSplitRegex = " |\n";
    private Map<String, Entity> entityMap;
    private Database database;
    private DomainTheoryData domainTheory;
    private int cycle;
    private List<Token> tokens;
    private List<String> facts;
    private Map<Token, Entity> resolveMap; // stores the entity the token will point to in next cycle
    private Map<Token, List<Token>> tokenClones; // when branching out, clones are stored for each original token
    private List<Token> addedTokens; // used by AND, where token clones do not need the record of their original
    private List<Token> finishedTokens;
    private List<Entity> entitiesInPath; // entities included in the paths of tokens in current cycle TODO should it be specific for each token?
    private List<String> initiatedNames;
    private List<String> terminatedNames;

    /* Candidate tokens property */
    private ListProperty<Entity> candidateActions = new SimpleListProperty<>(FXCollections.<Entity>observableArrayList());
    public ListProperty<Entity> candidateActionsProperty(){
        return candidateActions;
    }
    public void setCandidateActions(ObservableList<Entity> candidateActions) {
        this.candidateActions.set(candidateActions);
    }
    public final List<Entity> getCandidateActions(){
        return candidateActions.get();
    }


    /* ToBeResolved property : entities that will be resolved in current cycle */
    private ListProperty<Entity> toBeResolved = new SimpleListProperty<>(FXCollections.<Entity>observableArrayList());
    public ListProperty<Entity> toBeResolvedProperty(){
        return toBeResolved;
    }
    public final List<Entity> getToBeResolved(){
        return toBeResolved.get();
    }
    public void setToBeResolved(ObservableList<Entity> toBeResolved) {
        this.toBeResolved.set(toBeResolved);
    }


    /* SelectedActions property : actions that will be executed in current cycle */
    private ListProperty<Entity> selectedActions = new SimpleListProperty<>(FXCollections.<Entity>observableArrayList());
    public ListProperty<Entity> selectedActionsProperty(){
        return selectedActions;
    }
    public final List<Entity> getSelectedActions(){
        return selectedActions.get();
    }
    public void setSelectedActions(ObservableList<Entity> selectedActions) {
        this.selectedActions.set(selectedActions);
    }


    public ExecutionManager(Map<String, Entity> entityMap) {
        // initialise fields
        cycle = 0;
        this.entityMap = entityMap;
        database = Database.getInstance();
        domainTheory = DomainTheoryData.getInstance();
        tokens = new ArrayList<>();
        resolveMap = new HashMap<>();
        tokenClones = new HashMap<>();
        addedTokens = new ArrayList<>();
        finishedTokens = new ArrayList<>();
        entitiesInPath = new ArrayList<>();
        initiatedNames = new ArrayList<>();
        terminatedNames = new ArrayList<>();

        facts = Arrays.asList(database.getFacts().split(factSplitRegex));

        spawnNewTokens();

        database.factsProperty().addListener((observableValue, oldStr, newStr) -> {
            facts = Arrays.asList(newStr.split(factSplitRegex));
            updateAll();
        });
    }

    /**
     * select/deselect an action (add it to selectedActions)
     * @param e action to be added
     * @param select True if selecting, false if deselecting
     */
    public void selectAction(Entity e, boolean select){
        if(select){
            selectedActions.add(e);
        } else {
            selectedActions.remove(e);
        }
        updateAll();
    }

    public void proceed(){
        tokens.addAll(addedTokens);
        tokens.removeAll(finishedTokens);
        tokenClones.values().forEach(tokens::addAll);
        tokens.forEach(this::tryResolve);
        tokens.removeIf(t -> t.getCurrentEntity() == null); // get rid of finished tokens
        executeActions();
        selectedActions.clear();
        updateAll();

        tokens.forEach(Token::increment); // TODO keep the correct count (e.g. when cloned..)
        cycle++;
    }

    private void executeActions(){
        //update the database by using the action's post-conditions TODO


        //List<Postcondition> postconditions = domainTheory.getPostconditions().stream().filter(p -> p.getHead());

    }

    private void updateAll() {
        updateToBeResolved();
        updatePostConditions();
        updateCandidateTokens();
    }

    private void updatePostConditions() {
        // keep a list of conditions that will be 'initiated' and 'terminated' by the currently selected actions
        List<Postcondition> postconditions = domainTheory.getPostconditions();
        for(Postcondition postcondition : postconditions){
            // satisfied if all contents of the body are either in the database (facts) or is a selected action
            boolean satisfied = postcondition.getBody().stream().allMatch(b ->
                    facts.contains(b) || selectedActions.stream().anyMatch(sa -> sa.getName().equals(b)));
            if(satisfied){
                switch(postcondition.getType()){
                    case INITIATES:
                        initiatedNames.add(postcondition.getHead());
                        break;
                    case TERMINATES:
                        terminatedNames.add(postcondition.getHead());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void updateToBeResolved() {
        toBeResolved.clear();
        resolveMap.clear();
        addedTokens.clear();
        finishedTokens.clear();
        entitiesInPath.clear();
        initiatedNames.clear();
        terminatedNames.clear();

        // check if current token's entities hold
        tokens.forEach(t -> updatePath(t, t.getCurrentEntity()));
        toBeResolved.addAll(resolveMap.values());
    }

    // pass through all resolvable entities for the token and record the last in the path.
    private void updatePath(Token t, Entity current) {
        while(current != null){
            resolveMap.put(t, current);
            entitiesInPath.add(current);
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
                case ACTION:
                    //TODO only if selected by user (later by strategy)
                    if(selectedActions.contains(current)){
                        current = current.getNext();
                    } else {
                        current = null;
                    }
                    break;
                case OR:
                    //TODO clone token ... if one path finishes, remove the other.
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

    private void updateCandidateTokens() {
        List<Token> consideredTokens = new ArrayList<>();
        consideredTokens.addAll(tokens);
        consideredTokens.addAll(addedTokens);
        tokenClones.values().forEach(consideredTokens::addAll);
        consideredTokens.removeAll(finishedTokens);

        // add all relevant candidate actions
        getCandidateActions().addAll(consideredTokens.stream().map(resolveMap::get)
                .filter(e -> isCandidate(e) && !getCandidateActions().contains(e)).collect(Collectors.toList()));
        // remove anything that is not a candidate and is attached to a considered token
        getCandidateActions().removeIf(e -> !isCandidate(e) || !entitiesInPath.contains(e));
    }

    private boolean isCandidate(Entity e) {
        // all preconditions involving the token's entity must be satisfied,
        // and must be associated with a token
        if(e == null || e.getType() != EntityType.ACTION){
            return false;
        }

        List<Precondition> preconditions = getRelevantPreconditions(e);
        // e is a candidate if none of the conflicting conditions are met:
        return !preconditions.stream().anyMatch(precondition ->
                (conflictsWithSelectedActions(e, precondition)
                        || conflictsWithFacts(e, precondition, facts) // must be consistent with current facts to be a candidate
                        || conflictsWithFacts(e, precondition, getFactsNextCycle())) // must be able to execute actions next cycle without conflicts
        );
    }

    private List<Precondition> getRelevantPreconditions(Entity e) {
        return domainTheory.getPreconditions().stream()
                    .filter(p -> p.getConflictingNames().stream().anyMatch(name -> name.equals(e.getName())))
                    .collect(Collectors.toList());
    }

    private boolean conflictsWithFacts(Entity e, Precondition precondition, List<String> facts) {
        // precondition conflicts with current facts (e.g. false <- e & f)
        List<String> names = precondition.getConflictingNamesExcept(e.getName());
        List<String> conflictingNames = names.stream().filter(n -> !n.startsWith("!")).collect(Collectors.toList());
        List<String> requiredNames = names.stream().filter(n -> n.startsWith("!")).map(n -> n.substring(1)).collect(Collectors.toList());

        boolean meetsRequirement = facts.containsAll(requiredNames);
        boolean conflicts = facts.stream().anyMatch(conflictingNames::contains);
        return conflicts || !meetsRequirement;
    }

    // returns the facts for the next cycle (according to postconditions of selected actions)
    private List<String> getFactsNextCycle() {
        List<String> tempFacts = new ArrayList<>();
        tempFacts.addAll(facts);
        tempFacts.addAll(initiatedNames);
        tempFacts.removeAll(terminatedNames);
        return tempFacts;
    }

    private boolean conflictsWithSelectedActions(Entity e, Precondition precondition) {
        // precondition conflicts with currently selected actions (e.g. false <- e & sa)
        return selectedActions.stream().map(Entity::getName)
                .anyMatch(sa -> precondition.getConflictingNamesExcept(e.getName()).contains(sa));
    }

    // spawn tokens at the top of each reactive rule
    private void spawnNewTokens() {
        tokens.addAll(entityMap.values().stream().map(Token::new).collect(Collectors.toList()));
    }

    private void tryResolve(Token token) { //TODO consider goalDef
        // proceed to next step
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
        updateAll();
    }

    public void clear(){
        cycle = 0;
        tokens.clear();
        toBeResolved.clear();
        candidateActions.clear();
        selectedActions.clear();
        resolveMap.clear();
        tokenClones.clear();
        finishedTokens.clear();
        entitiesInPath.clear();
        addedTokens.clear();
        initiatedNames.clear();
        terminatedNames.clear();
    }
}
