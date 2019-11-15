/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissance;

import java.util.HashMap;
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
        BooleanDatabase booleanDB = new BooleanDatabase();
        for(Map<Variable, String> instance : db.getInstances()){
            Map<Variable,Boolean> res = new HashMap();
            for(Variable var : instance.keySet()){
                
                switch(instance.get(var)){
                    case "0":
                        res.put(var, Boolean.FALSE);
                        if(!ListContainsVar(booleanDB.getVariables(),var)){
                            booleanDB.getVariables().add(var);
                        }
                        break;
                        
                    case "1":
                        res.put(var, Boolean.TRUE);
                        if(!ListContainsVar(booleanDB.getVariables(),var)){
                            booleanDB.getVariables().add(var);
                        }
                        break;
                        
                    default:
                        Variable newvar = new Variable(var.getName()+"_"+instance.get(var),"True","False");
                        
                        if(ListContainsVar(booleanDB.getVariables(),newvar)){
                            System.out.println("default : var : "+ newvar.getName()+ " liste: " +ListToString(booleanDB.getVariables()));
                            addColumn(newvar, booleanDB);
                            booleanDB.getVariables().add(newvar);
                        } 
                        res.put(newvar, Boolean.TRUE);
                        break;
                }
                
            }
            booleanDB.getTransactions().add(res);
        }
        return booleanDB;
    }
    
    public static Boolean ListContainsVar(List<Variable> liste, Variable var){
        for(Variable listeVar: liste){
            if(var.getName() == listeVar.getName()){
                
                return true;
            }
        }
        return false;
    }
    
    public static String ListToString(List<Variable> liste){
        String res = "( ";
        for(Variable listeVar: liste){
        res += listeVar.getName() + ", ";
        }
        res += ")";
        return res;
    }
    
    public static void addColumn(Variable var, BooleanDatabase booleanDB){
        for(Map<Variable, Boolean> instance : booleanDB.getTransactions()){
            for(Variable key : instance.keySet()){
                if(key.getName() == var.getName()){
                    break;
                }
                instance.put(var, Boolean.FALSE);
            }
        }
    }
    
    public List<Variable> getOrderedVariables() {
        return orderedVariables;
    }

    public List<Map<Variable, String>> getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        String res = "Database : \n";
        for (int i = 0; i < 20; i++) {
            Map<Variable, String> instance = this.getInstances().get(i);
            for(Variable var : instance.keySet()){
                res += var.getName() + " : " + instance.get(var) + "; ";
            }
            res += "\n";
        }
        return res;
    }
    
    
}
