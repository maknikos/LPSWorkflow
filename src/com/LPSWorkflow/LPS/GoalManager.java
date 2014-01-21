package com.LPSWorkflow.LPS;

import model.AbstractOperator;
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
            return null; //TODO handle exception
        }
        AbstractOperator goalDefinition = (AbstractOperator) g.getNextDefinition(); //TODO null checking.... each step
        if(goalDefinition == null){
            return null; //TODO handle exception
        }

        return goalDefinition.getOperands();
    }
}
