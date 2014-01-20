package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.ReflectionHelper;
import model.Clause;
import model.GoalSet;
import model.GoalsList;

import java.util.ArrayList;

/**
 * Class dealing with Goals defined in the LPS program
 */
public class GoalManager {
    GoalSet goals;

    public GoalManager() {
        GoalsList gl = GoalsList.getInstance();
        goals = gl.getGoalsDefinitions();
    }

    public ArrayList getGoalDefinitions(String goal){

        Clause goalDefinition = goals.getGoal(goal).getNextDefinition(); //TODO null checking.... each step
        return (ArrayList) ReflectionHelper.getHiddenField(goalDefinition, "operands");
    }
}
