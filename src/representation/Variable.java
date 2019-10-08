/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 21907858
 */
public class Variable {
    public static final String FALSE = "non";
    public static final String TRUE = "oui";
    private String name;
    private Set<String> domain;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<String> getDomain() {
        return domain;
    }
    public void setDomain(Set<String> domain) {
        this.domain = domain;
    }
    
    public Variable(String name, String... domain){
        this.name = name;
        this.domain = new HashSet();
        for(String val: domain){
            this.domain.add(val);
        }
    }
    
    public static Variable makeBooleanVariable(String name){
        Variable variable = new Variable(name, "oui", "non");
        return variable;
    }
    
}
