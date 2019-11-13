/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.List;
import java.util.Map;
import representation.Variable;

/**
 *
 * @author axel
 */
public class Database {

    
    private List<Variable> orderedVariables;
    private List<Map<Variable, String>> instances;

    public Database(List<Variable> orderedVariables, List<Map<Variable, String>> instances) {
        this.orderedVariables = orderedVariables;
        this.instances = instances;
    }
    
    public static BooleanDatabase booleanConvert(Database db){
        BooleanDatabase booleanDatabase = new BooleanDatabase();
        for(Map<Variable, String> instance : db.getInstances()){
            
        }
        
    }
    
    public List<Variable> getOrderedVariables() {
        return orderedVariables;
    }

    public List<Map<Variable, String>> getInstances() {
        return instances;
    }
}
