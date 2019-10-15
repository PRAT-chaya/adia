//TP GREGORY BONNET PART2
package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import planning.Action;
import planning.ActionRule;
import representation.Variable;

/**
 *
 * @author axel
 */
public class HealthCare {
    
    List<Variable> maladies;
    List<Variable> syptms;
    public HealthCare(int n) {
        
        //Variables booléennes -> maladies
        Variable angina = new Variable("ANGINA","TRUE","FALSE");
        Variable flu = new Variable("FLU","TRUE","FALSE");
        Variable pox = new Variable("POX","TRUE","FALSE");
        Variable plague = new Variable("PLAGUE","TRUE","FALSE");
        maladies.add(angina); maladies.add(flu); maladies.add(pox); maladies.add(plague);
        //Variables à niveau -> symptômes
        Variable fever = new Variable("FEVER","HIGH","MEDIUM","LOW","NONE");
        Variable cough = new Variable("COUGH","HIGH","MEDIUM","LOW","NONE");
        Variable buttons = new Variable("BUTTONS","HIGH","MEDIUM","LOW","NONE");
        syptms.add(fever); syptms.add(cough); syptms.add(buttons); 

    }
     
    public Action createRandMedecine(){
        Action medecine = new Action();
        List<Variable> symptCopy = new ArrayList();
        for (Iterator<Variable> it = this.maladies.iterator(); it.hasNext();) {
            symptCopy.add(it.next());    
        }
        
        Random r = new Random();
        
        Map<Variable,String> preconditions = new HashMap();
        Map<Variable,String> effets = new HashMap();
        effets.put(this.syptms.remove(r.nextInt(this.syptms.size())), "none");
        ActionRule rule = new ActionRule(preconditions, effets);
        medecine.addRule(rule);
        
        
        
        
        return medecine;
    }
        
        
    
    public static void main(String[] args) {
        
        
    }
    
}
