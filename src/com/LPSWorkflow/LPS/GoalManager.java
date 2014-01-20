package com.LPSWorkflow.LPS;

import com.LPSWorkflow.common.ReflectionHelper;
import model.Clause;
import model.Goal;
import model.GoalSet;
import model.GoalsList;

import java.util.ArrayList;

/**
 * Class dealing with Goals defined in the LPS program
 */
public class GoalManager {
    public GoalManager() {
    }

    public ArrayList getGoalDefinitions(String goal){
        GoalSet goals = GoalsList.getInstance().getGoalsDefinitions();
        Goal g = goals.getGoal(goal);
        if(g == null){
            return new ArrayList(); //TODO handle exception
        }
        Clause goalDefinition = g.getNextDefinition(); //TODO null checking.... each step
        if(goalDefinition == null){
            return new ArrayList(); //TODO handle exception
        }
        return (ArrayList) ReflectionHelper.getHiddenField(goalDefinition, "operands");
    }
}
