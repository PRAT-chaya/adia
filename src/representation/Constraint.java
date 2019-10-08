/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation;

import java.util.List;
import java.util.Set;

/**
 *
 * @author 21907858
 */
public interface Constraint {    
    public Set<Variable> getScope();
    public boolean isSatisfiedBy(List<RestrictedDomain> assessment);
}
