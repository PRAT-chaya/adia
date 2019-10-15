/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;
import representation.RestrictedDomain;
import representation.Variable;

/**
 *
 * @author axel
 */
public class ActionRule {
    protected Map<Variable,String> preconditions;
    protected Map<Variable,String> effets;

    public ActionRule(Map<Variable,String> pre, Map<Variable, String> effets) {
        this.preconditions = pre;
        this.effets = effets;
    }

    public Map<Variable,String> getPreconditions() {
        return preconditions;
    }

    public Map<Variable, String> getEffets() {
        return effets;
    }
    
    
    
}
