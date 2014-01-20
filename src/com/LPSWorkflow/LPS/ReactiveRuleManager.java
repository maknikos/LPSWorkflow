package com.LPSWorkflow.LPS;

import model.ReactiveRuleSet;
import model.SimpleSentence;

import java.util.ArrayList;

import static com.LPSWorkflow.common.ReflectionHelper.getHiddenField;

/**
 * Class dealing with ReactiveRules from the parsed LPS program
 */
public class ReactiveRuleManager {

    public ReactiveRuleManager() {
    }

    /**
     *
     * @return Size of the reactive rules
     */
    public int size(){
        ArrayList reactiveRules = retrieveReactiveRules();
        if(reactiveRules == null)
            return 0;
        return reactiveRules.size();
    }

    /**
     * From ReactiveRule object, get the cause (antecedent) part.
     * @return cause part of the ReactiveRule
     */
    public String getReactiveRuleCause(int index) {
        ArrayList reactiveRules = retrieveReactiveRules();

        if(reactiveRules == null)
            return null;

        SimpleSentence s = (SimpleSentence) getHiddenField(reactiveRules.get(index), "causes");

        if(s == null)
            return null;

        return s.getName();
    }

    /**
     * From ReactiveRule object, get the goal (consequent) part.
     * @return goal part of the ReactiveRule
     */
    public String getReactiveRuleGoal(int index) {
        ArrayList reactiveRules = retrieveReactiveRules();

        if(reactiveRules == null)
            return null;

        SimpleSentence s = (SimpleSentence) getHiddenField(reactiveRules.get(index), "goal");

        if(s == null)
            return null;

        return s.getName();
    }

    private ArrayList retrieveReactiveRules() {
        return (ArrayList) getHiddenField(ReactiveRuleSet.getInstance(), "reactiveRules");
    }
}
