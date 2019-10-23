/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.HashSet;
import java.util.Set;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class FrequentItemsetMinerTest {

    public static void main(String[] args) {
        Set<Variable> variables = new HashSet();
        variables.add(Variable.makeBooleanVariable("A"));
        variables.add(Variable.makeBooleanVariable("B"));
        variables.add(Variable.makeBooleanVariable("C"));
        System.out.println(ntupletToString(variables));
        Set<Set<Variable>> ntuplets = FrequentItemsetMiner.ntupletsBuilder(2, variables);
        System.out.println(ntuplets);
        for (Set<Variable> ntuplet : ntuplets) {
            if (!ntuplet.contains(null)) {
                System.out.println(ntupletToString(ntuplet));
            }
        }
    }

    private static String ntupletToString(Set<Variable> ntuplet) {
        String output = "[";
        for (Variable var : ntuplet) {
            output += var.getName() + ", ";
        }
        output += "]";
        return output;
    }
}
