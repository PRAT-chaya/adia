/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import representation.Rule;
import representation.Variable;

/**
 *
 * @author 21600639
 */
public class Action {
    
    protected Set<Rule>  rules;

    public Action(Set<Rule> rules) {
        this.rules = rules;
    }
       
    public boolean is_applicable(State state){
        for(Iterator<Rule> ruleIt = rules.iterator(); ruleIt.hasNext();){
            if(state.satisfies(ruleIt.next().getPremise())){
                return true;
            }
        }
        return false;

    }
    
    
    
}
