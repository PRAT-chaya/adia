/**
 *
 * @author 21600639 : DENOUAL Axel
 * @author 21910036 : ROUSSEAU Alexy
 * @author 21907858 : SABATIER Brian
 * 
 */

package representation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RestrictedDomain {
    Variable variable;
    Set<String> subdomain;

    /**
     * Constructeur de notre objet RestrictedDomain
     * @param variable : Notre objet Variable
     * @param subdomain  : Le sous-domaine de valeurs que peuvent prendre ses variables
     */
    public RestrictedDomain(Variable variable, String... subdomain){
        this.variable = variable;
        this.subdomain = new HashSet();
        for(String val: subdomain){
            this.subdomain.add(val);
        }
    }
    
    public RestrictedDomain(Variable variable, Set<String> subdomain){
        this.variable = variable;
        this.subdomain = subdomain;
    }

    /**
     * Méthode permettant de retourner la variable concernée par le domaine restreint
     * @return Variable
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Méthode permettant d'assigner une variable à un  domaine Restreint
     * @param Variable l'objet Variable à assigner 
     */
    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    /**
     * Méthode permettant de récupérer le sous-domaine d'un domaine restreint
     * @return Set<String> Les valeurs du domaine restreint
     */
    public Set<String> getSubdomain() {
        return subdomain;
    }

    /**
     * Méthode permettant d'ajouter à un domaine restreint des valeurs de variables
     * @param subdomain Set<String> notre liste de valeurs à ajouter
     */
    public void setSubdomain(Set<String> subdomain) {
        this.subdomain = subdomain;
    }
    
    /**
     * Méthode permettant de comparer deux sous-domaine (identicité)
     * Le sous-domaine passé en paramètre est comparé à celui de l'objet courant.
     * @param subsubdomain
     * @return Boolean
     */
    public boolean subDomainContains(Set<String> subsubdomain){
        boolean subdomainContains = true;
        
        for (Iterator ssdIt = subsubdomain.iterator(); ssdIt.hasNext();)
        {
            if (!subdomain.contains(ssdIt.next())){
                subdomainContains = false;
            }                    
        }
        
        return subdomainContains;
    }
}
