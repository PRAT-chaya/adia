/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance.test;

import extractionConnaissance.AssociationRuleMiner;
import extractionConnaissance.BooleanDatabase;
import extractionConnaissance.FrequentItemsetMiner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import representation.Variable;
import java.util.Set;
import representation.Rule;

/**
 *
 * @author ordinaute
 */
public class AssociationRuleMinerTest {

    public static void main(String[] args) {
        List<Variable> vars = new ArrayList();
        vars.add(Variable.makeBooleanVariable("A"));
        vars.add(Variable.makeBooleanVariable("B"));
        vars.add(Variable.makeBooleanVariable("C"));
        vars.add(Variable.makeBooleanVariable("D"));
        vars.add(Variable.makeBooleanVariable("E"));

        int minFreq = 3;
        Map<Variable, Boolean> transaction1 = new HashMap();
        transaction1.put(vars.get(0), Boolean.TRUE);
        transaction1.put(vars.get(1), Boolean.TRUE);
        transaction1.put(vars.get(2), Boolean.TRUE);
        transaction1.put(vars.get(3), Boolean.TRUE);
        transaction1.put(vars.get(4), Boolean.TRUE);

        Map<Variable, Boolean> transaction2 = new HashMap();
        transaction2.put(vars.get(0), Boolean.TRUE);
        transaction2.put(vars.get(2), Boolean.TRUE);

        Map<Variable, Boolean> transaction3 = new HashMap();
        transaction3.put(vars.get(0), Boolean.TRUE);
        transaction3.put(vars.get(1), Boolean.TRUE);
        transaction3.put(vars.get(2), Boolean.TRUE);
        transaction3.put(vars.get(3), Boolean.TRUE);

        Map<Variable, Boolean> transaction4 = new HashMap();
        transaction4.put(vars.get(1), Boolean.TRUE);
        transaction4.put(vars.get(2), Boolean.TRUE);
        
        Map<Variable, Boolean> transaction5 = new HashMap();
        transaction5.put(vars.get(0), Boolean.TRUE);
        transaction5.put(vars.get(1), Boolean.TRUE);
        transaction5.put(vars.get(2), Boolean.TRUE);
        
        Map<Variable, Boolean> transaction6 = new HashMap();
        transaction5.put(vars.get(4), Boolean.TRUE);

        List<Map<Variable, Boolean>> transactions = new ArrayList();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
        FrequentItemsetMiner fiMiner = new FrequentItemsetMiner(new BooleanDatabase(vars, transactions));
        Map<Set<Variable>, Integer> frequentItemsets = fiMiner.frequentItemsets(minFreq);
        Set<Variable> scope = new HashSet();
        scope.addAll(fiMiner.getMinedScope());
        AssociationRuleMiner arMiner = new AssociationRuleMiner(frequentItemsets, scope);
        Map<Rule, Integer> minedRules = AssociationRuleMiner.ruleMiner(minFreq, frequentItemsets, scope);
        Set<Rule> rules = minedRules.keySet();
        for (Rule rule : rules){
            System.out.println(rule);
            System.out.println("FREQ: " + minedRules.get(rule));
        }
    }   

}
