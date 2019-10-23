/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class FrequentItemsetMiner {

    BooleanDatabase db;

    public FrequentItemsetMiner(BooleanDatabase db) {
        this.db = db;
    }

    public Map<Set<Variable>, Integer> frequentItemsets(int minFreq) {
        Map<Set<Variable>, Integer> frequentItemsets = new HashMap();
        Map<Variable, Integer> singletonCount = frequentSingletons(minFreq);
        for (Variable var : singletonCount.keySet()) {
        }
        return null;
    }

    private Map<Variable, Integer> frequentSingletons(int minFreq) {
        Map<Variable, Integer> singletonCount = new HashMap();
        for (Variable var : db.getVariables()) {
            singletonCount.put(var, 0);
        }
        for (Map<Variable, Boolean> transaction : db.getTransactions()) {
            for (Variable var : transaction.keySet()) {
                if (transaction.get(var)) {
                    int temp = (singletonCount.get(var)) + 1;
                    singletonCount.replace(var, temp);
                }
            }
        }
        for (Variable var : singletonCount.keySet()) {
            if (singletonCount.get(var) < minFreq) {
                singletonCount.remove(var);
            }
        }
        return singletonCount;
    }

    public static Map<Set<Variable>, Integer> ntupletCountBuilder(int n, Set<Variable> variables) {
        Map<Set<Variable>, Integer> ntupletCount = new HashMap();
        Set<Variable> temp = new HashSet();
        temp.addAll(variables);
        for (Iterator<Variable> varit = temp.iterator(); varit.hasNext();) {
            Set<Variable> ntuplet = new HashSet();
            Variable var = varit.next();
            ntuplet.add(var);
            temp.remove(var);

            ntupletCount.put(ntuplet, 0);
        }
        return ntupletCount;
    }

    public static Set<Set<Variable>> ntupletsBuilder(int n, Set<Variable> variables) {
        Set<Set<Variable>> ntuplets = new HashSet();
        Deque<Variable> temp = new LinkedList();
        temp.addAll(variables);
        for (Variable var : variables) {
            Set<Variable> ntuplet = new HashSet();
            ntuplet.add(temp.pollFirst());
            ntuplet = ntupletBuilder(n, temp, ntuplet);
            if (!ntuplet.isEmpty()) {
                ntuplets.add(ntuplet);
                System.out.println(var.getName());
            }
        }
        return ntuplets;
    }

    private static Set<Variable> ntupletBuilder(int n, Deque<Variable> variables, Set<Variable> ntuplet) {
        ntuplet.add(variables.pollFirst());
        while(ntuplet.size() < n+1){
            
            ntupletBuilder(i, variables, ntuplet);
        }
        return ntuplet;
    }
}
