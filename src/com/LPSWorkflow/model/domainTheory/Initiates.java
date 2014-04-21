package com.LPSWorkflow.model.domainTheory;

import java.util.List;

/**
 * Post-condition (initates)
 */
public class Initiates implements Postcondition{
    private String head;
    private List<String> body;

    public Initiates(String head, List<String> body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String getHead() {
        return head;
    }

    @Override
    public List<String> getBody() {
        return body;
    }

    @Override
    public PostconditionType getType() {
        return PostconditionType.INITIATES;
    }

    @Override
    public String toString() {
        String bodyStr = "";
        for(String n : body){
            bodyStr = bodyStr.concat(n + " & ");
        }
        if(body.size() > 0) {
            bodyStr = bodyStr.substring(0, bodyStr.length() - 2); // remove trailing &
        }
        return String.format("initiates(%s) <- %s", head, bodyStr);
    }
}
