/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representation.RestrictedDomain;
import representation.Rule;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class AssociationRuleMiner {

    private Map<Set<Variable>, Integer> frequentItemsets;
    private Set<Variable> scope;

    public AssociationRuleMiner(Map<Set<Variable>, Integer> frequentItemsets, Set<Variable> scope) {
        this.frequentItemsets = frequentItemsets;
        this.scope = scope;
    }

    public Set<Rule> ruleMiner(int minFreq, double minConf) {
        return AssociationRuleMiner.confidenceFilter(minConf, this.frequentItemsets,
                AssociationRuleMiner.ruleMiner(minFreq, this.frequentItemsets, this.scope));
    }

    public static Map<Rule, Integer> ruleMiner(int minFreq, Map<Set<Variable>, Integer> frequentItemsets, Set<Variable> scope) {
        Map<Rule, Integer> minedRules = new HashMap();
        for (int r = 0; r < scope.size(); r++) {
            Set<Rule> toCheck = new HashSet();
            toCheck.addAll(rulesBuilder(scope, r));
            for (Set<Variable> itemset : frequentItemsets.keySet()) {
                for(Rule rule : toCheck){
                    if (rule.isSatisfiedBy(itemset)){
                        if(minedRules.keySet().contains(rule)){
                            int val = minedRules.get(rule);
                            val++;
                            minedRules.replace(rule, val);
                        } else {
                            minedRules.put(rule, 1);
                        }
                    }
                }
            }
        }
        return minedRules;
    }

    public static Set<Rule> confidenceFilter(double confidence, Map<Set<Variable>, Integer> frequentItemsets, Map<Rule, Integer> minedRules) {
        return null;
    }

    public static Set<Rule> rulesBuilder(Set<Variable> itemset, int r) {
        Set<Rule> rules = new HashSet();
        //Set<Variable> varset = new HashSet();
        //varset.addAll(itemset);
        //rules.addAll(rulesBuilder(r, varset, rules));
        List<Variable[]> premisesVars = new ArrayList();
        Variable vars[] = new Variable[itemset.size()];
        int i = 0;
        for (Variable var : itemset) {
            vars[i] = var;
            i++;
        }
        buildCombination(vars, vars.length, r, premisesVars);
        for (Variable[] premiseVars : premisesVars) {
            List<RestrictedDomain> premise = new ArrayList();
            List<RestrictedDomain> conclusion = new ArrayList();
            Set<Variable> temp = new HashSet();
            temp.addAll(itemset);
            for (Variable var : premiseVars) {
                premise.add(new RestrictedDomain(var, Variable.TRUE));
                temp.remove(var);
            }
            for (Variable var : temp) {
                conclusion.add(new RestrictedDomain(var, Variable.TRUE));
            }
            rules.add(new Rule(premise, conclusion));
        }
        return rules;
    }

    static List<Rule> rulesBuilder(int r, Set<Variable> varset, List<Rule> rules) {
        List<Variable> varQ = new LinkedList();
        varQ.addAll(varset);
        for (int i = 1; i < varQ.size(); i++) {
            List<RestrictedDomain> premise = new ArrayList();
            List<Variable> temp = new LinkedList();
            temp.addAll(varset);
            Variable var = varQ.get(i - 1);
            temp.remove(var);
            premise.add(new RestrictedDomain(var, Variable.TRUE));
            rules.addAll(combi(r, temp, premise, new ArrayList(), i - 1));
        }
        return rules;
    }

    public static List<Rule> combi(int r, List<Variable> varQ,
            List<RestrictedDomain> premise, List<Rule> rules, int cursor) {
        if (premise.size() == r) {
            List<RestrictedDomain> conclusion = new ArrayList();
            List<Variable> temp = new ArrayList();
            temp.addAll(varQ);
            for (RestrictedDomain domain : premise) {
                temp.remove(domain.getVariable());
            }
            for (Variable var : temp) {
                conclusion.add(new RestrictedDomain(var, Variable.TRUE));
            }
            Rule rule = new Rule(premise, conclusion);
            rules.add(rule);
            if (cursor == (varQ.size())) {
                return rules;
            }
            premise = new ArrayList();
            int i = r - (cursor + 1);
            while (i < r) {
                premise.add(new RestrictedDomain(varQ.get(i), Variable.TRUE));
                i++;
            }
            cursor -= i;
            combi(r, varQ, premise, rules, cursor);
        } else {
            Variable var = varQ.get(cursor);
            premise.add(new RestrictedDomain(var, Variable.TRUE));
            combi(r, varQ, premise, rules, cursor + 1);
        }
        return null;
    }

    private static void combinationUtil(Variable arr[], Variable data[], int start,
            int end, int index, int r, List<Variable[]> output) {

        // Current combination is ready to be printed, print it 
        if (index == r) {
            Variable[] temp = new Variable[r];
            temp = data.clone();
            output.add(temp);
            return;
        }

        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r, output);
        }
    }

    // Builds all combinations of size r in arr[] of size n.
    public static void buildCombination(Variable arr[], int n, int r, List<Variable[]> output) {
        // A temporary array to store all combination one by one 
        Variable data[] = new Variable[r];

        // Print all combination using temprary array 'data[]' 
        combinationUtil(arr, data, 0, n - 1, 0, r, output);
    }
}
