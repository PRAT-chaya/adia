/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisfiability;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import representation.*;

/**
 *
 * @author ordinaute
 */
public class GAC {

    public static Domains GAC(Set<Variable> variables, Domains domains,
            Set<Constraint> constraints) {
        Set<Entry<Variable, Constraint>> tda = new HashSet();
        for (Variable var : variables) {
            for (Constraint constraint : constraints) {
                if (constraint.getScope().contains(var)) {
                    tda.add(new AbstractMap.SimpleEntry(var, constraint));
                }
            }
        }
        while (!tda.isEmpty()) {
            Entry<Variable, Constraint> arc = tda.iterator().next();
            tda.remove(arc);
            Domains wovar = domains;
            RestrictedDomain rd = wovar.getDomain(arc.getKey());
            wovar.getCollection().remove(rd);
            GAC.enforceConsistency(arc.getKey(), rd, arc.getValue(), wovar);
        }
        return domains;
    }

    protected static void enforceConsistency(Variable var, RestrictedDomain varDomain,
            Constraint constraint, Domains domains) {
        for (String value : varDomain.getSubdomain()) {
            if (!GAC.isConsistent(var, value, constraint, domains)) {
                varDomain.getSubdomain().remove(value);
            }
        }
    }

    protected static boolean isConsistent(Variable variable, String value,
            Constraint constraint, Domains domains) {
        return false;
    }

 

}
