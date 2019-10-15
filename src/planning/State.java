/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import representation.Variable;

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
        if(action.is_applicable(this)){
            for(Iterator<ActionRule> ruleIt = action.rules.iterator(); ruleIt.hasNext();){

                Map<Variable,String> effets; 
                
                effets = ruleIt.next().getEffets();
                
                if(nextState.satisfies(effets)){
                    
                    effets.entrySet().forEach((entry) -> {
                        nextState.affectation.replace(entry.getKey(), entry.getValue());
                    });  
                }

            }   
        }
        
        return nextState;
    }
    
    public State copy(){
        State stateCopy = new State();
        this.affectation.entrySet().forEach((entry) -> {
            stateCopy.add(entry.getKey(), entry.getValue());
        });
        return stateCopy;
    }
    
    
}
