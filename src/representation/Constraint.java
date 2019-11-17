/**
 *
 * @author 21600639 : DENOUAL Axel
 * @author 21910036 : ROUSSEAU Alexy
 * @author 21907858 : SABATIER Brian
 * 
 */

package representation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Constraint {
    /**
     * Retourne l'ensemble de variables sur lesquelles portent une contrainte
     * @return 
     */
    public Set<Variable> getScope();
    
    /**
     * Méthode permettant de récupérer une liste de RestrictedDomain contenant
     * A chaque ligne : un objet Variable et un Set<String>
     * Une contrainte restreint les valeurs que peuvent prendre une variable
     * @return List<RestrictedDomain> : Notre liste de domaines restreints
     */
    public List<RestrictedDomain> getDomains();
    
    /**
     * 
     * @param assessment
     * @return 
     */
    public boolean isSatisfiedBy(List<RestrictedDomain> assessment);
    
    public boolean isSatisfiedBy(Map<Variable, String> assessment);
    
    public boolean filter(List<RestrictedDomain> variables, Map<Variable, Set<String>> assessment);
}

