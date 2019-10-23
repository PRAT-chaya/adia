/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alexy
 */
public class ItemSet {
    private Set<Variable> variables = new HashSet<Variable>();
    private Integer frequence;
    private Double confiance;
    
    public ItemSet(Set<Variable> variables, Integer frequence, Double confiance) {
        this.variables = variables;
        this.frequence = frequence;
        this.confiance = confiance;
    }
  
    public Variable getVariable(Variable var) {
        for(Variable setvar : this.variables) {
            if(setvar == var) {
                return setvar;
            }
        }
        
        return null;
    }

    /**
     * @return the variables
     */
    public Set<Variable> getVariables() {
        return variables;
    }

    /**
     * @param variables the variables to set
     */
    public void setVariables(Set<Variable> variables) {
        this.variables = variables;
    }

    /**
     * @return the frequence
     */
    public Integer getFrequence() {
        return frequence;
    }

    /**
     * @param frequence the frequence to set
     */
    public void setFrequence(Integer frequence) {
        this.frequence = frequence;
    }

    /**
     * @return the confiance
     */
    public Double getConfiance() {
        return confiance;
    }

    /**
     * @param confiance the confiance to set
     */
    public void setConfiance(Double confiance) {
        this.confiance = confiance;
    }

    
    
}
