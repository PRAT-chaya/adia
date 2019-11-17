/**
 *
 * @author 21600639 : DENOUAL Axel
 * @author 21910036 : ROUSSEAU Alexy
 * @author 21907858 : SABATIER Brian
 * 
 */
package representation;

import java.util.HashSet;
import java.util.Set;

public class Variable {
    public static final String FALSE = "non";
    public static final String TRUE = "oui";
    private String name = "";
    private Set<String> domain;

     /**
     * Constructeur de notre classe, il faut lui spécifier un nom et un domaine
     * @param name
     * @param domain 
     */
    public Variable(String name, String... domain){
        this.name = name;
        this.domain = new HashSet();
        for(String val: domain){
            this.domain.add(val);
        }
    }
    
    /**
     * Retourne le nom de la variable
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /**
     * Modifie le nom de la variable
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Permet de récupérer un champ de valeurs possibles
     * à un objet Variable : c'est son domaine
     * @return domain : le domaine de variables
     */
    public Set<String> getDomain() {
        return domain;
    }
    
    /**
     * Permet d'attribuer un domaine de valeurs (possibles) à un objet variable.
     * C'est la définition du domaine.
     * @param domain 
     */
    public void setDomain(Set<String> domain) {
        this.domain = domain;
    }
    
    /**
     * Fonction statique permettant d'instancier une variable booléenne
     * Une var booléenne a "oui" ou "non" comme valeurs de domaine
     * @param name Le nom choisi de notre variable booléenne
     * @return variable : l'instance de notre Variable
     */
    public static Variable makeBooleanVariable(String name){
        Variable variable = new Variable(name, "oui", "non");
        return variable;
    }
    
}
