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
    
    public static void main(String[] args) {
        HealthCare hc = new HealthCare();
        Action a = hc.createRandMedecine();
        System.out.print(a);
        
    }
    
    List<Variable> maladies;
    List<Variable> syptms;
    public HealthCare() {
        
        //Variables booléennes -> maladies
        Variable angina = new Variable("ANGINA","TRUE","FALSE");
        Variable flu = new Variable("FLU","TRUE","FALSE");
        Variable pox = new Variable("POX","TRUE","FALSE");
        Variable plague = new Variable("PLAGUE","TRUE","FALSE");
        maladies = new ArrayList();
        maladies.add(angina); maladies.add(flu); maladies.add(pox); maladies.add(plague);
        //Variables à niveau -> symptômes
        Variable fever = new Variable("FEVER","HIGH","MEDIUM","LOW","NONE");
        Variable cough = new Variable("COUGH","HIGH","MEDIUM","LOW","NONE");
        Variable buttons = new Variable("BUTTONS","HIGH","MEDIUM","LOW","NONE");
        syptms = new ArrayList();
        syptms.add(fever); syptms.add(cough); syptms.add(buttons); 

    }
     
    public Action createRandMedecine(){
        Action medecine = new Action();
        
        //copie de la liste des symptomes dans symptCopy
        List<Variable> symptCopy = new ArrayList();
        for (Iterator<Variable> it = this.syptms.iterator(); it.hasNext();) {
            symptCopy.add(it.next());    
        }
        
        Random r = new Random();
        //fin de la copie
        
        ActionRule rule = new ActionRule();
        rule.ajoutEffet(symptCopy.remove(r.nextInt(symptCopy.size())), "none");
        medecine.addRule(rule);
        
        while(!symptCopy.isEmpty()){
            rule = new ActionRule();
            Variable symptome = symptCopy.remove(0);
            
            //copie de la liste des valeurs dans domaineCopy
            List<String> domaineCopy = new ArrayList();
            for (Iterator<String> domIt = symptome.getDomain().iterator(); domIt.hasNext();) {
                domaineCopy.add(domIt.next());    
            }
            //fin de la copie
            
            rule.ajoutEffet(symptome, domaineCopy.get(r.nextInt(domaineCopy.size())));
            medecine.addRule(rule);
        }
        
        
        
        return medecine;
    }
        
        
    
    
    
}
