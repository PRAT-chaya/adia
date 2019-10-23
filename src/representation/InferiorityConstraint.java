/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ordinaute
 */
public class InferiorityConstraint extends BinaryConstraint implements Constraint {

    public InferiorityConstraint(RestrictedDomain term1, RestrictedDomain term2){
        super(term1, term2);
    }
    
    @Override
    public boolean isSatisfiedBy(List<RestrictedDomain> assessment)
            throws IllegalArgumentException {
        List<RestrictedDomain> temp = new ArrayList();
        temp.addAll(assessment);
        for (RestrictedDomain tdrd : temp) {
            if (term1.getVariable() == tdrd.getVariable()) {
                if (tdrd.getSubdomain().size() > 1 && !tdrd.getSubdomain().isEmpty()) {
                    throw new IllegalArgumentException("Assessment should only have one value per variable");
                } else {
                    temp.remove(tdrd);
                    for (RestrictedDomain toCompare : temp) {
                        if (term2.getVariable() == toCompare.getVariable()) {
                            if (toCompare.getSubdomain().size() > 1 && !toCompare.getSubdomain().isEmpty()) {
                                throw new IllegalArgumentException("Assessment should only have one value per variable");
                            } else {
                                return InferiorityConstraint.inferiorityComparison(tdrd, toCompare);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> assessment) {
        if (assessment.containsKey(term1.getVariable())) {
            if (assessment.containsKey(term2.getVariable())) {
                return InferiorityConstraint.inferiorityComparison(
                        assessment.get(term1.getVariable()),
                        assessment.get(term2.getVariable())
                );
            }
        }
        return true;
    }

    @Override
    public boolean filter(List<RestrictedDomain> variables, Map<Variable, Set<String>> assessment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected static boolean inferiorityComparison(RestrictedDomain tdrd, RestrictedDomain toCompare)
            throws IllegalArgumentException {
        try {
            int val1 = Integer.parseInt(tdrd.getSubdomain().iterator().next());
            int val2 = Integer.parseInt(toCompare.getSubdomain().iterator().next());
            return (val1 < val2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot compare values that are not numbers");
        }
    }

    protected static boolean inferiorityComparison(String str1, String str2)
            throws IllegalArgumentException {
        try {
            int val1 = Integer.parseInt(str1);
            int val2 = Integer.parseInt(str2);
            return (val1 < val2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot compare values that are not numbers");
        }
    }
}
