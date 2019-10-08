/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc;

/**
 *
 * @author ordinaute
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representation.*;

/**
 *
 * @author ordinaute
 */
public class BacktrackSearch {

    protected final Set<Constraint> constraints;
    private final Set<Variable> scope;
    private Map<Variable, Iterator<String>> itMap;
    private Map<Variable, String> currValMap;
    private Set<Map<Variable, String>> allSolutions;
    private Iterator<Map<Variable, String>> solit;

    public BacktrackSearch(Set<Constraint> constraints) {
        this.itMap = new HashMap();
        this.currValMap = new HashMap();
        this.allSolutions = new HashSet();
        this.solit = allSolutions.iterator();
        this.constraints = constraints;
        scope = new HashSet();
        for (Constraint constraint : constraints) {
            for (Variable var : constraint.getScope()) {
                this.scope.add(var);
            }
        }
        for (Variable var : scope) {
            itMap.put(var, var.getDomain().iterator());
            currValMap.put(var, itMap.get(var).next());
        }

    }

    public Set<List<RestrictedDomain>> allSolutions() {
        Map<Variable, String> partialAssignment = new HashMap();
        Deque<Variable> unassignedVariables = new LinkedList();
        for (Variable var : scope) {
            unassignedVariables.addFirst(var);
        }
        Set<Map<Variable, String>> res = new HashSet();
        this.solutions(partialAssignment, unassignedVariables, res);
        Set<List<RestrictedDomain>> solutionSet = new HashSet();
        for (Map<Variable, String> solution : allSolutions) {
            List<RestrictedDomain> tempList = new ArrayList();
            for (Variable var : solution.keySet()) {
                tempList.add(new RestrictedDomain(var, solution.get(var)));
            }
            solutionSet.add(tempList);
        }
        return solutionSet;
    }

    protected void solutions(Map<Variable, String> partialAssignment,
            Deque<Variable> unassignedVariables, Set<Map<Variable, String>> accumulator) {
        String accuStr = "";
        for (Map<Variable, String> assignment : accumulator) {
            accuStr += "[" + assignmentToString(assignment) + "]\n";
        }
        System.out.println("\n***Accu***\n" + accuStr);
        if (!partialAssignment.keySet().equals(scope)) {        //Si l'assignation est incomplète, on assigne les variables manquantes
            assignVar(partialAssignment, unassignedVariables, accumulator); 
            solutions(partialAssignment, unassignedVariables, accumulator);
        } else if (accumulator.contains(partialAssignment)) {
            System.out.println(assignmentToString(partialAssignment));
            Variable cursor = unassignedVariables.peekFirst();
            Deque<Variable> temp = new LinkedList();
            for (Iterator<Variable> dupIt = unassignedVariables.iterator(); dupIt.hasNext();) {
                temp.addLast(dupIt.next());
            }
            temp.removeFirst();
            while (!itMap.get(cursor).hasNext()) {
                partialAssignment.remove(cursor);   //on enlève la variable entièrement testée de l'assignation
                reinitIt(cursor);                   //on réinitialise son itérateur de valeurs
                cursor = temp.pollFirst();          // le curseur remonte l'arbre d'un cran
                System.out.println("//cursor is " + cursor.getName() + "//");
                Variable rootVar = unassignedVariables.peekLast();
                if (cursor == rootVar && !itMap.get(rootVar).hasNext()) {
                    return;
                }
            }
            if (itMap.get(cursor).hasNext()) {
                reassignVar(cursor, itMap.get(cursor).next(), partialAssignment);
                temp.addFirst(cursor);
            }
            System.out.println("lastvar is " + cursor.getName() + ", value is " + currValMap.get(cursor));
            solutions(partialAssignment, unassignedVariables, accumulator);
        } else {
            Map<Variable, String> toAdd = new HashMap();
            for (Variable var : partialAssignment.keySet()) {
                toAdd.put(var, partialAssignment.get(var));
            }
            if (verifyConstraints(partialAssignment)) {
                System.out.println("***Constraint satisfied***");
                allSolutions.add(toAdd);
            }

            accumulator.add(toAdd);
            solutions(partialAssignment, unassignedVariables, accumulator);
        }
    }

    public List<RestrictedDomain> solution() {
        List<RestrictedDomain> solution = new ArrayList();
        if (allSolutions.isEmpty()) {
            allSolutions();
        } else {
            solit = allSolutions.iterator();
            Map<Variable, String> solutionMap;
            solutionMap = solit.next();
            for (Variable var : solutionMap.keySet()) {
                solution.add(new RestrictedDomain(var, solutionMap.get(var)));
            }
        }
        return solution;
    }

    private String assignmentToString(Map<Variable, String> assignment) {
        String varStr = "";
        for (Variable var : assignment.keySet()) {
            varStr += var.getName() + ", " + assignment.get(var) + " ";
        }
        return varStr;
    }

    private void assignVar(Map<Variable, String> partialAssignment,
            Deque<Variable> unassignedVariables, Set<Map<Variable, String>> accumulator) {
        Iterator<Variable> descVarIt = unassignedVariables.descendingIterator();
        while (descVarIt.hasNext()) {
            Variable var = descVarIt.next();
            if (!partialAssignment.keySet().contains(var)) {
                System.out.println("\nvar assigned");
                partialAssignment.put(var, currValMap.get(var));
                System.out.println(var.getName());
            }
        }
    }

    private void reinitIt(Variable cursor) {
        itMap.replace(cursor, cursor.getDomain().iterator());
        currValMap.replace(cursor, itMap.get(cursor).next());
    }

    private void reassignVar(Variable cursor, String newVal, Map<Variable, String> partialAssignment) {
        currValMap.replace(cursor, newVal);
        partialAssignment.replace(cursor, newVal);
    }

    private boolean verifyConstraints(Map<Variable, String> assignment) {
        boolean isSatisfied = true;
        String assignOut = "";
        for (Constraint constraint : constraints) {
            List<RestrictedDomain> assessment = new ArrayList();
            for (Variable var : scope) {
                assignOut += var.getName() + " " + assignment.get(var) + ", ";
                assessment.add(new RestrictedDomain(var, assignment.get(var)));
            }
            if (!constraint.isSatisfiedBy(assessment)) {
                isSatisfied = false;
            }
        }
        System.out.println(assignOut);
        return isSatisfied;
    }
}
