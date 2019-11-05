/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.Map;
import java.util.Set;
import representation.Constraint;
import representation.Rule;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class AssociationRuleMiner {
    private Map<Set<Variable>, Integer> frequentItemsets;

    public AssociationRuleMiner(Map<Set<Variable>, Integer> frequentItemsets) {
        this.frequentItemsets = frequentItemsets;
    }
    public Set<Rule> ruleMiner(int minFreq, double confidence){
        return AssociationRuleMiner.confidenceFilter(confidence, frequentItemsets,
        AssociationRuleMiner.ruleMiner(minFreq, frequentItemsets));
    }
    
    public static Set<Rule> ruleMiner(int minFreq, Map<Set<Variable>, Integer> frequentItemsets){
        
    }
    
    public static Set<Rule> confidenceFilter(double confidence, Map<Set<Variable>, Integer> frequentItemsets, Set<Rule> minedRules){
        
    }
    
    public static Rule ruleBuilder(Set<Variable>){
        
    }
}
