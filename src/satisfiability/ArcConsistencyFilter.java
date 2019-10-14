 
package satisfiability;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.Constraint;
import representation.Variable;
import representation.RestrictedDomain;

/**
 * A class for enforcing generalized arc consistency to domains wrt to variables.
 * The algorithms are brute-force: for deciding whether a value is GAC wrt a constraint, at
 * worst all tuples in the Cartesian product of the variables in the scope of the
 * constraint are tested. 
 */
public class ArcConsistencyFilter {

	protected Set<Constraint> constraints;
	
	public ArcConsistencyFilter(Set<Constraint> constraints) {
		this.constraints = constraints;
	}
	
	/**
	 * Filters given domains by removing values which are not arc consistent
	 * with respect to a given set of constraints.
	 * @param constraints A set of constraints
	 * @param domains A map from (at least) the variables occurring in the scope of
	 * the constraint to domains
	 * @throws IllegalArgumentException if a variable occurring in the scope of some
	 * constraint is mapped to no domain
	 */
	public void enforceArcConsistency (List<RestrictedDomain> domains)
	throws IllegalArgumentException {
		boolean hasChanged = true;
		while (hasChanged) {
			for (Constraint constraint: this.constraints) {
				hasChanged = ArcConsistencyFilter.enforceArcConsistency(constraint, domains);
			}
		}
	}
	
        /**
	 * Filters given domains by removing values which are not arc consistent
	 * with respect to a given constraint.
	 * @param constraint A constraint
	 * @param domains A map from (at least) the variables occurring in the scope of
	 * the constraint to domains
	 * @return true if at least one domain has changed, false otherwise
	 * @throws IllegalArgumentException if a variable occurring in the scope of the
	 * constraint is mapped to no domain
	 */
	public static boolean enforceArcConsistency(Constraint constraint, List<RestrictedDomain> domains) {
		boolean hasChanged = false;
                
                try {
                    Set<Variable> scope = constraint.getScope();
                    
                    for(Variable var : scope) {
                        hasChanged = false;
                        Set<String> filteredDomain = new HashSet(); // Notre domaine filtré maj une fois le filtrage effectué
                        
                        for(String varValue : var.getDomain()) {
                            for(RestrictedDomain domain : domains) {
                                for(String domainValue : domain.getVariable().getDomain())
                                {
                                    if(domainValue.equals(varValue)) {
                                        hasChanged = true;
                                        filteredDomain.add(varValue);
                                    }
                                }
                            }
                            
                            if(!hasChanged) {
                                throw new Exception("La variable n'est pas dans le domaine donné");
                            }
                            
                            // Si c'est consistant on arrête les traitements
                            if(ArcConsistencyFilter.isConsistent(var, varValue, constraint, domains)) {
                                return true;
                            }
                            else {
                                var.setDomain(filteredDomain);
                                
                                /*
                                * Modification du restricted domain & appel récursif
                                */
                                //domains.get(domains.size()-1).setSubdomain(filteredDomain);                                
                                ArcConsistencyFilter.enforceArcConsistency(constraint, domains);
                            }
                        }
                    }
                 } catch(Exception e) {
                    System.out.println(e.getMessage());
                 }
                    
                    
                
		// on boucle sur les variables de la contraintes (var)
            // si var n'est pas dans le domains donnée, alors erreurs
            // si oui 
                // récupérer les valeurs de var dans domains 
                    //if ( ! ArcConsistencyFilter.isConsistent(constraint.getScope(),, constraint, domains) ) {
                        // faites les modification nécéssaire
                // enlever toutes les valeurs non viables
        // en cas de changement
            // appel récusive sur le rest de domains
           // return true;
        //} else 
          //  return false;

        }
	/**
	 * Decides whether a given value is arc consistent for a given variable wrt a given constraint
	 * and given domains.
	 * @param variable A variable
	 * @param value A value
	 * @param constraint A constraint
	 * @param domains A map from (at least) the variables occurring in the scope of
	 * the constraint to domains (except possibly the given variable)
	 * @return true if the given value is arc consistent for the given variable
	 * @throws IllegalArgumentException if a variable occurring in the scope of the
	 * constraint (except possibly the given variable) is mapped to no domain
	 */
	public static boolean isConsistent (Variable variable, String value, Constraint constraint, List<RestrictedDomain> domains)
	throws IllegalArgumentException {
		Deque<Variable> unassignedVariables = new LinkedList<>(constraint.getScope());
		unassignedVariables.remove(variable);
		Map<Variable, String> partialAssignment = new HashMap<>();
		partialAssignment.put(variable, value);
		return ArcConsistencyFilter.isConsistent(partialAssignment, unassignedVariables, constraint, domains);
	}
	
	// Helper method ===================================================
	
	protected static boolean isConsistent (Map<Variable, String> partialAssignment, Deque<Variable> unassignedVariables, Constraint constraint, List<RestrictedDomain> domains) {
		if (unassignedVariables.isEmpty()) {
			return constraint.isSatisfiedBy(partialAssignment);
		} else {
			Variable var = unassignedVariables.pop();
			if (!domains.contains(var) ) { // ajout containsVar dans RestrictedDomain
				throw new IllegalArgumentException("Variable " + var + " occurs in " + constraint + " but has no domain in " + domains);
			} else {
                           
                        }
                // vérifier la consistance (satisfiabilité) par un appel récursif partialAssignment sur les variables du domains, sachant que le test d'arrêt est déjà fait
                // et que si tout est vrai on retour vrai
                
                // sinon on retourne false 
			}
		}
	}

}