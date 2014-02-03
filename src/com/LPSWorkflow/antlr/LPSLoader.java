package com.LPSWorkflow.antlr;

import com.LPSWorkflow.model.Action;
import com.LPSWorkflow.model.PartialOrder;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads the parsed LPS program information into a structured data
 */
public class LPSLoader extends LPSBaseListener {
    public enum Parse{
        NONE, REACTIVE_RULES, GOALS
    }

    private Map<Object, Object> reactiveRuleConnections;
    private Map<Object, Object> goalConnections;
    private Parse currentParseTarget;

    public LPSLoader() {
        this.reactiveRuleConnections = new HashMap<Object, Object>();
        this.goalConnections = new HashMap<Object, Object>();
        this.currentParseTarget = Parse.NONE;
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
    public void enterR(@NotNull LPSParser.RContext ctx) {
        // reactive rule, r, always has 2 child formulas
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2){
            handleErrorCase();
            return;
        }
        getConnections().put(formulas.get(0), formulas.get(1));
    }

    @Override
    public void enterAtomic(@NotNull LPSParser.AtomicContext ctx) {
        // Atomic case of a Formula. Only has an AtomContext as a child
        if(ctx.getChildCount() < 1){
            handleErrorCase();
            return;
        }

        // Replace all AtomicContexts with Action object
        Action action = new Action(ctx.atom().getText());

        replaceKey(ctx, action);
        replaceValues(ctx, action);
    }

    @Override
    public void enterSequence(@NotNull LPSParser.SequenceContext ctx) {
        // Sequences are Formulas in the form: Formula ',' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2){
            handleErrorCase();
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
        if (ctx.getChildCount() < 1) {
            handleErrorCase();
            return;
        }

        LPSParser.FormulaContext formula = ctx.formula();
        // Connection from the Sequence starts from the second Formula
        replaceKey(ctx, formula);
        // Connections to the Sequence are reconnected to the first Formula
        replaceValues(ctx, formula);

    }

    @Override
    public void enterPartialOrder(@NotNull LPSParser.PartialOrderContext ctx) {
        // PartialOrders are Formulas in the form: Formula '||' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2){
            handleErrorCase();
            return;
        }

        String firstFormula = formulas.get(0).getText();
        String secondFormula = formulas.get(1).getText();

        PartialOrder partialOrder = new PartialOrder(firstFormula, secondFormula);

        // Replace the PartialOrderContext with PartialOrder entity
        replaceKey(ctx, partialOrder);
        replaceValues(ctx, partialOrder);
    }

    @Override
    public void enterConcurrent(@NotNull LPSParser.ConcurrentContext ctx) {
        // Concurrent formulas are in the form: Formula ':' Formula
        List<LPSParser.FormulaContext> formulas = ctx.formula();

        if(formulas.size() < 2){
            handleErrorCase();
            return;
        }

        String firstFormula = formulas.get(0).getText();
        String secondFormula = formulas.get(1).getText();

        PartialOrder partialOrder = new PartialOrder(firstFormula, secondFormula);

        // Replace the PartialOrderContext with PartialOrder entity
        replaceKey(ctx, partialOrder);
        replaceValues(ctx, partialOrder);
    }

    @Override
    public void enterG(@NotNull LPSParser.GContext ctx) {
    }

    private void replaceValues(Object oldValue, Object newValue) {
        Map<Object, Object> connections = getConnections();
        if(connections.containsValue(oldValue)){
            for(Object key : connections.keySet()){
                if(connections.get(key).equals(oldValue)){
                    connections.put(key, newValue); //replace with the first formula
                }
            }
        }
    }

    private void replaceKey(Object oldKey, Object newKey) {
        Map<Object, Object> connections = getConnections();
        if(connections.containsKey(oldKey)){
            Object value = connections.get(oldKey);
            connections.remove(oldKey);
            connections.put(newKey, value);
        }
    }

    private void handleErrorCase() {
        // TODO error case handling?
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

    public Map<Object, Object> getReactiveRuleConnections() {
        return reactiveRuleConnections;
    }

    public Map<Object, Object> getGoalConnections() {
        return goalConnections;
    }
}
