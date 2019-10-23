/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ordinaute
 */
public class Domains {

    private List<RestrictedDomain> domains;

    public Domains(List<RestrictedDomain> domains) {
        this.domains = domains;
    }

    public List<RestrictedDomain> getCollection() {
        return domains;
    }

    public RestrictedDomain getDomain(Variable var) throws IllegalArgumentException {
        for (RestrictedDomain domain : this.domains) {
            if (domain.getVariable() == var) {
                return domain;
            }
        }
        throw new IllegalArgumentException("No domain associated with this variable");
    }

    public void setDomains(List<RestrictedDomain> domains) {
        this.domains = domains;
    }
    
    public void add(RestrictedDomain domain){
        this.domains.add(domain);
    }
    
    public void remove(RestrictedDomain domain) throws IllegalArgumentException {
        if (this.domains.contains(domain)) {
            this.domains.remove(domain);
        } else {
            throw new IllegalArgumentException("No such domain in domains");
        }
    }

    public Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet();
        for (RestrictedDomain domain : this.domains) {
            variables.add(domain.getVariable());
        }
        return variables;
    }

    public boolean containsVar(Variable var) {
        for (RestrictedDomain domain : this.domains) {
            if (domain.getVariable() == var) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasEmpty(){
        for (RestrictedDomain rd : this.domains){
            if (rd.getSubdomain().isEmpty()){
                return true;
            }
        }
        return false;
    }
}
