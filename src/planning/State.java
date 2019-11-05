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

    private Map<Variable, String> affectation;

    public State() {
        this.affectation = new HashMap();
    }

    public void add(Variable var, String val) {
        this.affectation.put(var, val);
    }

    public Map<Variable,String> getAffectation() {
        return this.affectation;
    }
    
    public boolean satisfies(Map<Variable,String> partialState){
        if(partialState.isEmpty()){return true;}
        for (Variable x : partialState.keySet()) {
            if (!(affectation.containsKey(x)) || !(partialState.get(x).equals(affectation.get(x)))) {
                return false;
            }
        }
        return true;
    }

    public State apply(Action action) {

        if (action.is_applicable(this)) {
            for (ActionRule rule : action.getRules()) {
                if (this.satisfies(rule.getPreconditions())) {
                    for (Variable var : rule.getEffets().keySet()) {
                        this.affectation.replace(var, rule.getEffets().get(var));
                    }
                }

            }
        }

        return this;
    }

    public State copy() {
        State stateCopy = new State();
        this.affectation.entrySet().forEach((entry) -> {
            stateCopy.add(entry.getKey(), entry.getValue());
        });
        return stateCopy;
    }

    public void printAffectation() {
        for (Variable var : this.affectation.keySet()) {
            System.out.println(var.getName() + " => " + this.affectation.get(var));
        }
        System.out.println("}");
    }

}
