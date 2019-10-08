/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author 21907858
 */
public class RestrictedDomain {
    Variable variable;
    Set<String> subdomain;

    public RestrictedDomain(Variable variable, String... subdomain){
        this.variable = variable;
        this.subdomain = new HashSet();
        for(String val: subdomain){
            this.subdomain.add(val);
        }
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Set<String> getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(Set<String> subdomain) {
        this.subdomain = subdomain;
    }
    
    public boolean subDomainContains(Set<String> subsubdomain){
        boolean subdomainContains = true;
                for (Iterator ssdIt = subsubdomain.iterator(); ssdIt.hasNext();){
                    if (!subdomain.contains(ssdIt.next())){
                        subdomainContains = false;
                }                    
            }
        return subdomainContains;
    }
}
