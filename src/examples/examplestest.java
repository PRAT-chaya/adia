/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import representation.IncompatibilityConstraint;
import representation.RestrictedDomain;
import representation.Rule;
import representation.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static representation.Representations.initSet;

/**
 *
 * @author ordinaute
 */
public class examplestest {

    public static void main(String[] args) {
        Examples examples = new Examples();
        Map rules = examples.getRules();

        Set<String> bmh = initSet(new String[]{"bas", "moyen", "haut"});
        Variable fievre = new Variable("fievre", bmh);
        Set<String> ouiNonSet = initSet(new String[]{"oui", "non"});
        Variable hypothermie = new Variable("hypothermie", ouiNonSet);
        Variable virus = new Variable("virus", ouiNonSet);

        Set<String> mhSet = new HashSet();
        mhSet.add("moyen");
        mhSet.add("haut");

        //Règle 9
        Map<Variable, RestrictedDomain> premise9 = new HashMap();
        premise9.put(fievre, new RestrictedDomain("fievre", mhSet));
        Map<Variable, RestrictedDomain> conclusion9 = new HashMap();
        conclusion9.put(hypothermie,
                new RestrictedDomain(
                        "hypothermie",
                        initSet(new String[]{"oui"})));
        IncompatibilityConstraint rule9 = new IncompatibilityConstraint(premise9, conclusion9);

        Map<Variable, String> assessment = new HashMap();
        assessment.put(fievre, "haut");
        assessment.put(hypothermie, "non");
        assessment.put(virus, "oui");

        String satisfaction = (rule9.isSatisfiedBy(assessment)) ? "satisfied" : "unstatisfied";
        System.out.println(satisfaction + "\n_________");

        Map<Variable, RestrictedDomain> premise1 = rule9.getPremise();
        Map<Variable, RestrictedDomain> premise2 = ((IncompatibilityConstraint) examples.getRules()
                .get("9")).getPremise();
        Map<Variable, RestrictedDomain> conclusion1 = rule9.getConclusion();
        Map<Variable, RestrictedDomain> conclusion2 = ((IncompatibilityConstraint) examples
                .getRules().get("9")).getConclusion();

        //String premiseEqual = (premise1.equals(premise2))
        //        ? "equal" : "unequal";
        //System.out.println(premiseEqual);
        
        System.out.println(rule9.classType);
        displayIter(premise1);
        displayIter(conclusion1);
        System.out.println(((Rule) examples
                .getRules().get("9")).classType);
        displayIter(premise2);
        displayIter(conclusion2);
    }

    private static void displayIter(Map<Variable, RestrictedDomain> term) {
        for (Iterator<Variable> testIt = term.keySet().iterator();
                testIt.hasNext();) {
            Variable var = testIt.next();
            String out = var.getName();
            for (Iterator<String> strIt = var.getDomain().iterator();
                    strIt.hasNext();) {
                out += ", " + strIt.next();
            }
            System.out.println(out);
        }
    }
}
