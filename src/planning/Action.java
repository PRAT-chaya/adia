/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import representation.Variable;

/**
 *
 * @author 21600639
 */
public class Action {
    
    protected Set<ActionRule>  rules;

    public Action() {
        this.rules = new HashSet();
    }
    
    public void addRule(ActionRule rule){
        this.rules.add(rule);
    }
       
    public boolean is_applicable(State state){
        for(Iterator<ActionRule> ruleIt = rules.iterator(); ruleIt.hasNext();){
            if(state.satisfies(ruleIt.next().getPreconditions())){
                return true;   
            }
        }
        return false;

    }

    @Override
    public String toString() {
        String ts = "Action{ \n";
        for(Iterator<ActionRule> ruleIt = rules.iterator(); ruleIt.hasNext();){
            ts += ruleIt.next().toString() + "\n";

        }
        ts += '}';
        return ts;
    }
    
    
    
    
}
