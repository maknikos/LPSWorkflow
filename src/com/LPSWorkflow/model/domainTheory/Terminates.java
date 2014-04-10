package com.LPSWorkflow.model.domainTheory;

import com.LPSWorkflow.model.abstractComponent.Entity;

import java.util.List;

/**
 * Post-condition (terminates)
 */
public class Terminates implements Postcondition{
    private Entity head;
    private List<Entity> body;

    public Terminates(Entity head, List<Entity> body) {
        this.head = head;
        this.body = body;
    }

    public Entity getHead() {
        return head;
    }

    @Override
    public List<Entity> getBody() {
        return body;
    }

    @Override
    public PostconditionType getType() {
        return PostconditionType.TERMINATES;
    }

    @Override
    public String toString() {
        String headStr = head.getName();
        String bodyStr = "";
        for(Entity e : body){
            bodyStr = bodyStr.concat(e.getName() + " & ");
        }
        if(body.size() > 0) {
            bodyStr = bodyStr.substring(0, bodyStr.length() - 2); // remove trailing &
        }
        return String.format("terminates(%s) <- %s", headStr, bodyStr);
    }
}
