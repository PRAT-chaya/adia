/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class BooleanDatabase {
    private List<Variable> variables;
    private List<Map<Variable, Boolean>> transactions;
    public BooleanDatabase(List<Variable> variables,
            List<Map<Variable,Boolean>> transactions){
        this.variables = variables; 
        this.transactions = transactions;
    }
    
    public BooleanDatabase(){
        this.variables = new ArrayList();
        this.transactions = new ArrayList();
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public void setTransactions(List<Map<Variable, Boolean>> transactions) {
        this.transactions = transactions;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public List<Map<Variable, Boolean>> getTransactions() {
        return transactions;
    }
    
    @Override
    public String toString() {
        String res = "BooleanDatabase : \n";
        for (int i = 0; i < 20; i++) {
            Map<Variable, Boolean> instance = this.getTransactions().get(i);
            for(Variable var : instance.keySet()){
                res += var.getName() + " : " + instance.get(var) + "; ";
            }
            res += "\n";
        }
        return res;
    }
}
