/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import examples.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 21907858
 */
public class Representations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Variable angine = Variable.makeBooleanVariable("angine");
        Variable fievre = new Variable("fievre", "bas", "moyen", "haut");
        Variable vaccin = Variable.makeBooleanVariable("vaccin");
        Variable toux = Variable.makeBooleanVariable("toux");
        Variable grippe = Variable.makeBooleanVariable("grippe");
        Variable virus = Variable.makeBooleanVariable("virus");
        Variable hypothermie = Variable.makeBooleanVariable("hypothermie");
        Variable fatigue = Variable.makeBooleanVariable("fatigue");
        Variable priseSirop = Variable.makeBooleanVariable("priseSirop");
        Variable boutons = Variable.makeBooleanVariable("boutons");
        Variable oedeme = Variable.makeBooleanVariable("oedeme");
        Variable allergieSucre = new Variable("allergieSucre", "bas", "moyen", "haut");

        Map<Variable, String> assessment = new HashMap();
        assessment.put(angine, Variable.TRUE);
        assessment.put(fievre, "haut");
        assessment.put(vaccin, Variable.TRUE);
        assessment.put(toux, Variable.FALSE);
        assessment.put(grippe, Variable.FALSE);
        assessment.put(virus, Variable.FALSE);
        assessment.put(hypothermie, Variable.TRUE);
        assessment.put(fatigue, Variable.TRUE);
        assessment.put(priseSirop, Variable.TRUE);
        assessment.put(boutons, Variable.TRUE);
        assessment.put(oedeme, Variable.TRUE);
        assessment.put(allergieSucre, "haut");
        System.out.println("Instanciation1: angine:oui, fievre:haut, toux:non, virus:non, hypothermie:oui, fatigue:oui, priseSirop: oui, boutons:oui, oedeme:oui, allergieSucre: haut");

        Examples examples = new Examples();
        List<Rule> rules = examples.getRules();
        int i = 1;
        for (Rule rule : rules) {
            List<RestrictedDomain> premise = rule.getPremise();

            String output = "Rule: " + i;
            List<RestrictedDomain> assessmentL = new ArrayList();
            for (RestrictedDomain rd : premise){
                assessmentL.add(rd);
            }
            if (rule.isSatisfiedBy(assessmentL)) {
                System.out.println(output + " is satisfied");
            } else {
                System.out.println(output + " is not satisfied\n");
            }
            i++;
        }

    }

    public static Set<String> initSet(String[] vals) {
        return new HashSet<>(Arrays.asList(vals));
    }

    private static String varIt(Set<Variable> variables) {
        String varStr = "Variables:";
        for (Iterator<Variable> varIt = variables.iterator(); varIt.hasNext();) {
            Variable var = varIt.next();
            varStr += " " + var.getName();
        }
        varStr += "\n";
        return varStr;
    }

    private static String valIt(Set<RestrictedDomain> subdomains) {
        String valStr = "Variables:";
        for (Iterator<Variable> varIt = variables.iterator(); varIt.hasNext();) {
            Variable var = varIt.next();
            varStr += " " + var.getName();
        }
        varStr += "\n";
        return varStr;
    }

}
