/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ordinaute
 */
public abstract class BinaryConstraint implements Constraint {
    protected Set<Variable> scope;
    protected RestrictedDomain term1;
    protected RestrictedDomain term2;
    
    protected BinaryConstraint(RestrictedDomain term1, RestrictedDomain term2){
        this.scope = new HashSet();
        this.scope.add(term1.getVariable());
        this.scope.add(term2.getVariable());
        this.term1 = term1;
        this.term2 = term2;
    }
    
    @Override
    public Set<Variable> getScope(){
        return this.scope;
    }

    @Override
    public List<RestrictedDomain> getDomains(){
        List<RestrictedDomain> domains = new ArrayList();
        domains.add(this.term1);
        domains.add(this.term2);
        return domains;
    }
}
