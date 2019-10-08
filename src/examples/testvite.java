/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import representation.*;
import ppc.*;

/**
 *
 * @author ordinaute
 */
public class testvite {
   public static void main(String[]args){

        Variable angine = new Variable("angine", "oui", "non");

        Variable fievre = new Variable("fievre", "bas", "moyen", "haut");

        Variable vaccin = new Variable("vaccin", "oui", "non");
        Variable toux = new Variable("toux", "oui", "non");
        Variable grippe = new Variable("grippe", "oui", "non");
        Variable virus = new Variable("virus", "oui", "non");
        Variable hypothermie = new Variable("hypothermie", "oui", "non");
        Variable fatigue = new Variable("fatigue", "oui", "non");
        Variable priseSirop = new Variable("priseSirop", "oui", "non");
        Variable boutons = new Variable("boutons", "oui", "non");
        Variable oedeme = new Variable("oedeme", "oui", "non");
        Variable allergieSucre = new Variable("allergieSucre", "bas", "moyen", "haut");
        
        Map<Variable, RestrictedDomain> premise1 = new HashMap();
        
        premise1.put(angine, new RestrictedDomain("angine", "oui"));
        Map<Variable, RestrictedDomain> conclusion1 = new HashMap();
        conclusion1.put(fievre, new RestrictedDomain("fièvre", "moyen", "haut"));        
        Rule rule1 = new Rule(premise1, conclusion1);
        //System.out.println("Règle1: \'L'angine provoque une fièvre haute ou moyenne\'");
        
        Map<Variable, RestrictedDomain> premise2 = new HashMap();
        premise2.put(angine, new RestrictedDomain("angine", "oui"));
        Map<Variable, RestrictedDomain> conclusion2 = new HashMap();
        conclusion2.put(toux, new RestrictedDomain("toux", "oui"));        
        Rule rule2 = new Rule(premise2, conclusion2);
        System.out.println("Règle2: \'L'angine provoque une toux\'");
        
        Map<Variable, RestrictedDomain> premise9 = new HashMap();
        premise9.put(fievre, new RestrictedDomain("fievre", "moyen", "haut"));
        Map<Variable, RestrictedDomain> conclusion9 = new HashMap();
        conclusion9.put(hypothermie, new RestrictedDomain("hypothermie", "oui"));
        IncompatibilityConstraint rule9 = new IncompatibilityConstraint(premise9, conclusion9);
        /*System.out.println("Règle9: \'On ne peut pas à la fois avoir une fièvre haute"
                + " ou moyenne et être en hypothermie\'");
        */
        Set <Constraint> constraints = new HashSet();
        constraints.add(rule2);
        BacktrackInProgress bts = new BacktrackInProgress(constraints);
        Set<Variable> variables = new HashSet();
        variables.add(angine);
        variables.add(toux);
        bts.setVariables(variables);
        
        displayInstance(bts.solution());
        displayInstance(bts.solution());
        
        
/*
        Map<Variable, String> assessment1 = new HashMap();
        assessment1.put(angine, "oui");
        assessment1.put(toux, "oui");
        
         Map<Variable, String> assessment2 = new HashMap();
        assessment2.put(angine, "non");
        assessment2.put(toux, "oui");
        
        Map<Variable, String> assessment3 = new HashMap();
        assessment3.put(angine, "oui");
        assessment3.put(toux, "non");
        
        Map<Variable, String> assessment4 = new HashMap();
        assessment4.put(angine, "non");
        assessment4.put(toux, "non");

        /*
        String satisfaction1 = (rule1.isSatisfiedBy(assessment)) ? "satisfied" : "unstatisfied";
        System.out.println("Rule: " + satisfaction1 + "\n_________");
*/
       /* 
        String satisfaction = (rule2.isSatisfiedBy(assessment1)) ? "satisfied" : "unstatisfied";
        System.out.println("angine_oui, toux_oui : " + satisfaction + "\n_________");
        
        satisfaction = (rule2.isSatisfiedBy(assessment2)) ? "satisfied" : "unstatisfied";
        System.out.println("angine_non, toux_oui : " + satisfaction + "\n_________");
        
        satisfaction = (rule2.isSatisfiedBy(assessment3)) ? "satisfied" : "unstatisfied";
        System.out.println("angine_oui, toux_non : " + satisfaction + "\n_________");
        
        satisfaction = (rule2.isSatisfiedBy(assessment4)) ? "satisfied" : "unstatisfied";
        System.out.println("angine_non, toux_non : " + satisfaction + "\n_________");

*/
        
        
   }
   
   public static void displayInstance(Map<Variable,String> map){
       Set<Variable> variables = map.keySet();
       for (Iterator<Variable> varIt = variables.iterator(); varIt.hasNext();){
           Variable variable = varIt.next();
           String value = map.get(variable);
           System.out.println(variable.getName() + ", " + value);
       }
   }
}
