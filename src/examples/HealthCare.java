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

import planning.PlanningProblem;
import planning.State;
import planning.ActionRule;

/**
 *
 * @author axel
 */
public class HealthCare {
    
    List<Variable> maladies;
    List<Variable> syptms;
    public HealthCare() {
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
    
    public HealthCare(List<Variable> maladies, List<Variable> syptms) {
        this.maladies = maladies;
        this.syptms = syptms;
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
    
    public State genInitialState() {      
        State initialState = new State();
        
        int choixMaladie = (int) Math.random() * (maladies.size() - 0);
        int etatMaladie = (int) Math.random() * (maladies.get(choixMaladie).getDomain().size() - 0);
        int nbSymptomes = (int) Math.random() * (syptms.size() - 0);
        
        Variable maladie = maladies.get(choixMaladie);
        
        ArrayList<String> temp = new ArrayList();
        temp.addAll(maladie.getDomain());
        
        initialState.add(maladie, temp.get(etatMaladie));
        int i = 0;
        boolean tryAgain;
    
        int choixSymptome;

        List<Variable> symptomes = new ArrayList();
        symptomes.addAll(syptms);
        
        for(Iterator<Variable> symptomesIt = symptomes.iterator(); symptomesIt.hasNext();) {
            Variable symptome = symptomesIt.next();
            choixSymptome = (int) Math.random() * (symptomes.size() - 0);
            Variable symptomeChoisi = symptomes.get(choixSymptome);
            int etatSymptome = (int) Math.random() * (symptome.getDomain().size() - 0);
            
            temp.removeAll(temp);
            temp.addAll(symptome.getDomain());

            initialState.add(symptome, temp.get(etatSymptome));
            symptomes.remove(choixSymptome);
        }
        
        return initialState;
    }
}
