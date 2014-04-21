package com.LPSWorkflow.model.domainTheory;

import java.util.List;

/**
 * Post-condition (terminates)
 */
public class Terminates implements Postcondition{
    private String head;
    private List<String> body;

    public Terminates(String head, List<String> body) {
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
        return PostconditionType.TERMINATES;
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
        return String.format("terminates(%s) <- %s", head, bodyStr);
    }
}
