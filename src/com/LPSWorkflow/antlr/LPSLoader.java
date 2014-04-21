package com.LPSWorkflow.antlr;

import com.LPSWorkflow.model.abstractComponent.*;
import com.LPSWorkflow.model.domainTheory.Initiates;
import com.LPSWorkflow.model.domainTheory.Postcondition;
import com.LPSWorkflow.model.domainTheory.Precondition;
import com.LPSWorkflow.model.domainTheory.Terminates;
import com.LPSWorkflow.model.message.MessageData;
import com.LPSWorkflow.model.message.MessageType;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Loads the parsed LPS program information into a structured data. The data includes
 * a map of connections of entities in reactive rule definitions and goal definitions,
 * a map of roots for reactive rules and goal definitions,
 * a list of names of fluents.
 */
public class LPSLoader extends LPSBaseListener {
    //TODO LHS of R context must be conditions (not actions)
    //TODO each side of Concurrent (:) must be conditions (not actions)
    //TODO it assumes that LHS of reactive rules cannot have Sequences

    // enum indicating what the parser is parsing at the moment
    public enum Parse{
        NONE, REACTIVE_RULES, FLUENTS, GOALS, DOMAIN_THEORY
    }

    private Map<Object, Object> reactiveRuleRoots;
    private Map<Object, Object> goalRoots;
    private Map<Object, Object> reactiveRuleConnections;
    private Map<Object, Object> goalConnections;
    private List<String> fluents;
    private List<Precondition> preconditions;
    private List<Postcondition> postconditions;
    private Parse currentParseTarget;
    private MessageData messageData;


    public LPSLoader() {
        this.fluents = new ArrayList<>();
        this.preconditions = new ArrayList<>();
        this.postconditions = new ArrayList<>();
        this.reactiveRuleConnections = new HashMap<>();
        this.goalConnections = new HashMap<>();
        this.reactiveRuleRoots = new HashMap<>();
        this.goalRoots = new HashMap<>();
        this.currentParseTarget = Parse.NONE;
        this.messageData = MessageData.getInstance();
    }

    @Override
    public void enterGoals(@NotNull LPSParser.GoalsContext ctx) {
        currentParseTarget = Parse.GOALS;
    }

    @Override
    public void exitGoals(@NotNull LPSParser.GoalsContext ctx) {
        currentParseTarget = Parse.NONE;
    }

    @Override
    public void enterReactiveRules(@NotNull LPSParser.ReactiveRulesContext ctx) {
        currentParseTarget = Parse.REACTIVE_RULES;
    }

    @Override
    public void exitReactiveRules(@NotNull LPSParser.ReactiveRulesContext ctx) {
        currentParseTarget = Parse.NONE;
    }

    @Override
    public void enterFluents(@NotNull LPSParser.FluentsContext ctx) {
        currentParseTarget = Parse.FLUENTS;
    }

    @Override
    public void exitFluents(@NotNull LPSParser.FluentsContext ctx) {
        currentParseTarget = Parse.NONE;
    }

    @Override
    public void enterDomainTheory(@NotNull LPSParser.DomainTheoryContext ctx) {
        currentParseTarget = Parse.DOMAIN_THEORY;
    }

    @Override
    public void exitDomainTheory(@NotNull LPSParser.DomainTheoryContext ctx) {
        currentParseTarget = Parse.NONE;
    }

    @Override
    public void enterR(@NotNull LPSParser.RContext ctx) {
        // reactive rule, r, always has 2 child formulas
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2
                || formulas.get(0).exception != null
                || formulas.get(1).exception != null){
            sendErrorMessage("Parsing 'Reactive Rule' failed. Invalid format.");
            return;
        }
        getRoots().put(formulas.get(0), formulas.get(1));
    }

    @Override
    public void enterAtomic(@NotNull LPSParser.AtomicContext ctx) {
        // Atomic case of a Formula. Only has an AtomContext as a child
        LPSParser.AtomContext atom = ctx.atom();
        if(atom == null || atom.exception != null){
            sendErrorMessage("Parsing 'Atomic' element failed. Invalid format.");
            return;
        }

        replaceKey(ctx, atom);
        replaceValues(ctx, atom);
    }

    @Override
    public void enterSequence(@NotNull LPSParser.SequenceContext ctx) {
        // Sequences are Formulas in the form: Formula ',' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2
                || formulas.get(0).exception != null
                || formulas.get(1).exception != null){
            sendErrorMessage("Parsing 'Sequence' element failed. Invalid format.");
            return;
        }

        LPSParser.FormulaContext firstFormula = formulas.get(0);
        LPSParser.FormulaContext secondFormula = formulas.get(1);
        // Make a connection between the formulas in the sequence
        getConnections().put(firstFormula, secondFormula);

        // Connection from the Sequence starts from the second Formula
        replaceKey(ctx, secondFormula);
        // Connections to the Sequence are reconnected to the first Formula
        replaceValues(ctx, firstFormula);
    }

    @Override
    public void enterBracket(@NotNull LPSParser.BracketContext ctx) {
        // Formulas of the form: '(' Formula ')'
        LPSParser.FormulaContext formula = ctx.formula();
        if (formula == null || formula.exception != null) {
            sendErrorMessage("Parsing brackets failed. Invalid formula between brackets");
            return;
        }
        // Connection from the Sequence starts from the second Formula
        replaceKey(ctx, formula);
        // Connections to the Sequence are reconnected to the first Formula
        replaceValues(ctx, formula);

    }

    @Override
    public void enterPartialOrder(@NotNull LPSParser.PartialOrderContext ctx) {
        // PartialOrders are Formulas in the form: Formula '||' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2
                || formulas.get(0).exception != null
                || formulas.get(1).exception != null){
            sendErrorMessage("Parsing 'PartialOrder' element failed. Invalid format.");
            return;
        }

        String firstFormula = formulas.get(0).getText();
        String secondFormula = formulas.get(1).getText();
        List<Entity> entities = new ArrayList<>();

        Action first = new Action(firstFormula);
        Action second = new Action(secondFormula);
        first.setNext(new Exit());
        second.setNext(new Exit());
        entities.add(first);
        entities.add(second);
        PartialOrder partialOrder = new PartialOrder(entities);

        // Replace the PartialOrderContext with PartialOrder entity
        replaceKey(ctx, partialOrder);
        replaceValues(ctx, partialOrder);
    }

    @Override
    public void enterConcurrent(@NotNull LPSParser.ConcurrentContext ctx) {
        // Concurrent formulas are in the form: Formula ':' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();
        //TODO can it manager complex combination of other formulas?
        if(formulas.size() < 2
                || formulas.get(0).exception != null
                || formulas.get(1).exception != null){
            sendErrorMessage("Parsing 'Concurrent' element failed. Invalid format.");
            return;
        }

        String firstFormula = formulas.get(0).getText();
        String secondFormula = formulas.get(1).getText();

        Concurrent concurrent = new Concurrent(firstFormula, secondFormula);

        // Replace the PartialOrderContext with PartialOrder entity
        replaceKey(ctx, concurrent);
        replaceValues(ctx, concurrent);
    }

    @Override
    public void enterG(@NotNull LPSParser.GContext ctx) {
        // Goal, g, always has an Atom and a Formula as children.
        LPSParser.AtomContext atom = ctx.atom();
        LPSParser.FormulaContext formula = ctx.formula();
        if(atom == null || atom.exception != null){
            sendErrorMessage("Parsing 'Goal definition' failed. Invalid head of the definition.");
            return;
        } else if(formula == null || formula.exception != null){
            sendErrorMessage("Parsing 'Goal definition' failed. Invalid tail of the definition.");
            return;
        }
        getRoots().put(atom, formula);
    }

    @Override
    public void enterAtom(@NotNull LPSParser.AtomContext ctx) {
        if(ctx.getChildCount() < 1 || ctx.exception != null){
            sendErrorMessage("Parsing 'Atom' element failed. Invalid format.");
            return;
        }
        if(currentParseTarget == Parse.FLUENTS){
            // when parsing fluents, just add their names
            fluents.add(ctx.getText());
        } else if(currentParseTarget == Parse.DOMAIN_THEORY) {
            // for domain theories, skip.
            return;
        }else {
            Action action = new Action(ctx.getText());
            // Replace all AtomicContexts with Action object
            replaceKey(ctx, action);
            replaceValues(ctx, action);
        }

    }

    @Override
    public void enterF(@NotNull LPSParser.FContext ctx) {
        super.enterF(ctx);
    }

    @Override
    public void enterInitiates(@NotNull LPSParser.InitiatesContext ctx) {
        if(ctx.exception != null){
            sendErrorMessage("Parsing 'initiates' element failed. Invalid format.");
            return;
        }
        String head = ctx.atom().getText();
        LPSParser.ConjunctionContext conjunction = ctx.conjunction();
        List<String> names = makeConjunctionIntoList(conjunction);

        Initiates initiates = new Initiates(head, names);
        postconditions.add(initiates);
    }

    @Override
    public void enterTerminates(@NotNull LPSParser.TerminatesContext ctx) {
        if(ctx.exception != null){
            sendErrorMessage("Parsing 'terminates' element failed. Invalid format.");
            return;
        }

        String head = ctx.atom().getText();
        LPSParser.ConjunctionContext conjunction = ctx.conjunction();
        List<String> names = makeConjunctionIntoList(conjunction);

        Terminates terminates = new Terminates(head, names);
        postconditions.add(terminates);
    }

    @Override
    public void enterPrecond(@NotNull LPSParser.PrecondContext ctx) {
        if(ctx.exception != null){
            sendErrorMessage("Parsing 'Precond' element failed. Invalid format.");
            return;
        }
        LPSParser.ConjunctionContext conjunction = ctx.conjunction();
        List<String> names = makeConjunctionIntoList(conjunction);
        preconditions.add(new Precondition(names));
    }

    private List<String> makeConjunctionIntoList(LPSParser.ConjunctionContext conjunction) {
        return conjunction.atom().stream().map(LPSParser.AtomContext::getText).collect(Collectors.toList());
    }

    private void replaceValues(Object oldValue, Object newValue) {
        Map<Object, Object> connections = getConnections();
        if(connections.containsValue(oldValue)){
            // replace with the new value
            connections.keySet().stream().filter(key -> connections.get(key).equals(oldValue))
                    .forEach(key -> connections.put(key, newValue));
        }

        Map<Object, Object> roots = getRoots();
        if(roots.containsValue(oldValue)){
            roots.keySet().stream().filter(key -> roots.get(key).equals(oldValue))
                    .forEach(key -> roots.put(key, newValue));
        }
    }

    private void replaceKey(Object oldKey, Object newKey) {
        Map<Object, Object> connections = getConnections();
        if(connections.containsKey(oldKey)){
            Object value = connections.get(oldKey);
            connections.remove(oldKey);
            connections.put(newKey, value);
        }

        Map<Object, Object> roots = getRoots();
        if(roots.containsKey(oldKey)){
            Object value = roots.get(oldKey);
            roots.remove(oldKey);
            roots.put(newKey, value);
        }
    }

    private void sendErrorMessage(String msg) {
        messageData.sendMessage(msg, MessageType.ERROR);
    }

    private Map<Object, Object> getConnections(){
        switch(currentParseTarget){
            case REACTIVE_RULES:
                return reactiveRuleConnections;
            case GOALS:
                return goalConnections;
            default:
                return null;
        }
    }

    private Map<Object, Object> getRoots(){
        switch(currentParseTarget){
            case REACTIVE_RULES:
                return reactiveRuleRoots;
            case GOALS:
                return goalRoots;
            default:
                return null;
        }
    }


    public List<String> getFluents() {
        return fluents;
    }

    public List<Precondition> getPreconditions(){
        return preconditions;
    }

    public List<Postcondition> getPostconditions(){
        return postconditions;
    }

    public Map<Object, Object> getReactiveRuleConnections() {
        return reactiveRuleConnections;
    }

    public Map<Object, Object> getGoalConnections() {
        return goalConnections;
    }

    public Map<Object, Object> getReactiveRuleRoots() {
        return reactiveRuleRoots;
    }

    public Map<Object, Object> getGoalRoots() {
        return goalRoots;
    }
}
