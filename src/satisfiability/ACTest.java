/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisfiability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import representation.RestrictedDomain;
import representation.Rule;
import representation.Variable;

/**
 *
 * @author ordinaute
 */
public class ACTest {
    public static void main(String[] args){
		Variable x1 = new Variable("x1", "1");
		Variable x2 = new Variable("x2", "1", "2","3");
		Variable x3 = new Variable("x3", "1", "2", "3");

		Set<Variable> allVariables = new HashSet<>(Arrays.asList(x1, x2, x3));

		// Rule : (x1<x2 && x2=x3)
		// ======================

		List<RestrictedDomain> premise = new ArrayList<>();
		premise.add(new RestrictedDomain(x1, Variable.FALSE));
		premise.add(new RestrictedDomain(x1, "0"));

		List<RestrictedDomain> conclusion = new ArrayList<>();
		conclusion.add(new RestrictedDomain(x2, "1"));
		conclusion.add(new RestrictedDomain(x3, "2", "6"));

		Rule rule = new Rule(premise, conclusion);
        
        GeneralizedArcConsistency gac = new GeneralizedArcConsistency();
    }
}
