/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representation.Rule;
import representation.Variable;
import representation.RestrictedDomain;

/**
 *
 * @author 21600639
 */
public class State {
    
    private Map<Variable,String> affectation;

    public State() {
        this.affectation = new HashMap();
    }
    
    public void add(Variable var, String val){
        this.affectation.put(var, val);
    }
    
    public boolean satisfies(Map<Variable,String> partialState){
        for (Iterator<Variable> It = partialState.keySet().iterator(); It.hasNext();) {
            Variable x = It.next();
            if(!(affectation.containsKey(x) || !partialState.get(x).equals(affectation.get(x)))){
                return true;
            }
        }
        return false;
        
    }
    
    public State apply(Action action){
        
        State nextState = this.copy(); 
        for(Iterator<Rule> ruleIt = action.rules.iterator(); ruleIt.hasNext();){
            
            Map<Variable,String> effets;  
            effets = ruleIt.next().getConclusion();
            if(nextState.satisfies(effets)){
                for(Iterator<RestrictedDomain> rdIt = effets.iterator(); rdIt.hasNext();){
                    
                }  
            }
            
        }
        return nextState;
    }
    
    public State copy(){
        State stateCopy = new State();
        for(Map.Entry<Variable, String> entry : this.affectation.entrySet()){
            stateCopy.add(entry.getKey(), entry.getValue());
        }
        return stateCopy;
    }
    
    
}
