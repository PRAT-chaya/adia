/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.List;
import java.util.Map;
import representation.Variable;

/**
 *
 * @author alexy
 */
public class Database {

    private List<Variable> orderedVariables;
    private List<Map<Variable, String>> instances;
    
    public Database(List<Variable> orderedVariables, List<Map<Variable, String>> instances) {
        this.orderedVariables = orderedVariables;
        this.instances = instances;
    }
    
    public List<Variable> getVariables() {
        return this.orderedVariables;
    }
    
    public List<Map<Variable, String>> getInstances() {
        return this.instances;
    }
    
}
