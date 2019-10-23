/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 21907858
 */
public class Rule implements Constraint {

    private Set<Variable> scope;
    protected List<RestrictedDomain> premise;
    protected List<RestrictedDomain> conclusion;

    public Rule(List<RestrictedDomain> premise, List<RestrictedDomain> conclusion) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.scope = new HashSet();
        for (int i = 0; i<premise.size(); i++){
            scope.add(premise.get(i).getVariable());
        }
        for (int i = 0; i<conclusion.size(); i++){
            scope.add(conclusion.get(i).getVariable());
        }
    }

    @Override
    public Set<Variable> getScope() {
        return scope;
    }
    
    @Override
    public List<RestrictedDomain> getDomains(){
        List<RestrictedDomain> domains = new ArrayList();
        domains.addAll(premise);
        domains.addAll(conclusion);
        return domains;
    }

    public List<RestrictedDomain> getPremise() {
        return premise;
    }

    public List<RestrictedDomain> getConclusion() {
        return conclusion;
    }

    @Override
    public boolean isSatisfiedBy(List<RestrictedDomain> assessment) {
<<<<<<< Updated upstream
        boolean isSatisfied = true;
        //Pour chaque variable de l'instance testée
        for (int i = 0; i < assessment.size(); i++) {
            Variable assessmentPremVar = assessment.get(i).getVariable();
            //Si la prémisse est concernée par la variable
            for (int x = 0; x < premise.size(); x++) {
                if (premise.get(x).getVariable() == assessmentPremVar) {
                    /*Si le sous-domaine de la variable tel qu'incluse dans la prémisse
                    contient la valeur associée à cette variable dans l'instance testée*/
                    if (premise.get(x).subDomainContains(assessment.get(i).getSubdomain())) {
                        //Pour chaque variable de l'instance testée
                        for (int j = 0; j < assessment.size(); j++) {
                            Variable assessmentCclVar = assessment.get(j).getVariable();
                            /*Si le domaine de la variable tel qu'incluse 
                            dans la conclusion contient la valeur associée
                            à cette variable dans l'instance testée*/
                            for (int y = 0; y < conclusion.size(); y++) {
                                if (conclusion.get(y).getVariable() == assessmentCclVar) {
                                    if (!conclusion.get(y).subDomainContains(assessment.get(j).getSubdomain())) {
                                        isSatisfied = false;
                                    }
                                }
                            }
=======
        boolean isSatisfied = true; // Si l'assignation n'est pas concernée par la prémisse elle est satisfaisante
        if (this.isInPremiseScope(assessment)) {
            for (RestrictedDomain crd : conclusion) {
                isSatisfied = false;
                for (RestrictedDomain assessmentRd : assessment) {
                    for (String val : assessmentRd.getSubdomain()) {
                        if (crd.getSubdomain().contains(val)) {
                            isSatisfied = true;
>>>>>>> Stashed changes
                        }
                    } else {
                        isSatisfied = true;
                    }
                }
            }
        }

        return isSatisfied;
    }

<<<<<<< Updated upstream
//    @Override
//    public boolean isSatisfiedBy(Map<Variable, String> assessment) {
//        boolean isSatisfied = true;
//        //Pour chaque variable de l'instance testée
//        for (Iterator<Variable> premAIt = assessment.keySet()
//                .iterator(); premAIt.hasNext();) {
//            Variable assessmentPremVar = premAIt.next();
//            //Si la prémisse est concernée par la variable
//            if (premise.containsKey(assessmentPremVar)) {
//                Set<String> premVarSubDomain = premise.get(assessmentPremVar).getSubdomain();
//                /*Si le sous-domaine de la variable tel qu'incluse dans la prémisse
//                contient la valeur associée à cette variable dans l'instance testée*/
//                if (premVarSubDomain.contains(assessment.get(assessmentPremVar))) {
//                    //Pour chaque variable de l'instance testée
//                    for (Iterator<Variable> cclAIt = assessment.keySet().iterator();
//                            cclAIt.hasNext();) {
//                        Variable assessmentCclVar = cclAIt.next();
//                        /*Si le domaine de la variable tel qu'incluse 
//                        dans la conclusion contient la valeur associée
//                        à cette variable dans l'instance testée*/
//                        if (conclusion.containsKey(assessmentCclVar)) {
//                            Set<String> cclVarSubDomain = conclusion.get(assessmentCclVar).getSubdomain();
//                            /*Si la valeur associée à la variable n'est pas dans
//                            le sous-domaine de cette même variable tel qu'inclus
//                            dans la conclusion*/
//                            if (!cclVarSubDomain.contains(assessment.get(assessmentCclVar))) {
//                                isSatisfied = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return isSatisfied;
//    }
=======
    @Override
    public boolean isSatisfiedBy(Map<Variable, String> assessment) {
        boolean isSatisfied = true; // Si l'assignation n'est pas concernée par la prémisse elle est satisfaisante
        if (this.isInPremiseScope(assessment)) {
            for (RestrictedDomain crd : conclusion) {
                isSatisfied = false;
                for (Variable var : assessment.keySet()) {
                    if (crd.getSubdomain().contains(assessment.get(var))) {
                        isSatisfied = true;
                    }
                }
            }
        }
        return isSatisfied;
    }

    public boolean isInPremiseScope(List<RestrictedDomain> assessment) {
        boolean isInPremiseScope = true;
        for (RestrictedDomain prd : premise) {
            for (RestrictedDomain assessmentRd : assessment) {
                if (prd.getVariable() == assessmentRd.getVariable()) {
                    for (String val : assessmentRd.getSubdomain()) {
                        if (!prd.getSubdomain().contains(val)) {
                            isInPremiseScope = false;
                        }
                    }
                }
            }
        }
        return isInPremiseScope;
    }

    public boolean isInPremiseScope(Map<Variable, String> assessment) {
        boolean isInPremiseScope = true;
        for (RestrictedDomain prd : premise) {
            for (Variable var : assessment.keySet()) {
                if (prd.getVariable() == var) {
                    if (!prd.getSubdomain().contains(assessment.get(var))) {
                        isInPremiseScope = false;
                    }
                }
            }
        }
        return isInPremiseScope;
    }

    @Override
    public boolean filter(List<RestrictedDomain> assessment, Map<Variable, Set<String>> unassignedVars) {
        boolean filtered = false;
        for (RestrictedDomain assessmentRd : assessment) {

        }
        return filtered;
    }
>>>>>>> Stashed changes
}
