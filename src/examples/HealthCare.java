  //TP GREGORY BONNET PART2
package examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import planning.Action;
import planning.ActionRule;
import planning.PlanningProblem;
import planning.State;
import representation.Variable;

/**
 *
 * @author axel
 */
public class HealthCare {

    public static void main(String[] args) {
        HealthCare hc = new HealthCare();
        Set<Action> actions = new HashSet();
        actions.addAll(hc.makeNMedecine(10, 3));
        actions.add(hc.getGuerison());
        actions.addAll(hc.getMedecine());
        State initialState = hc.genInitialState(new Random());
        State finalState = new State();
        for (Variable var : hc.maladies) {
            if (initialState.getAffectation().containsKey(var)) {
                finalState.add(var, "FALSE");
            }
        }
        for (Variable var : hc.syptms) {
            finalState.add(var, "NONE");
        }
        PlanningProblem pb = new PlanningProblem(initialState, finalState.getAffectation(), actions);
        pb.printProblem();
        System.out.println();
        System.out.println("***DFS***");
        pb.printPlan(pb.dfs(4));
        System.out.println(pb.getDfsProbe());
        System.out.println();
        System.out.println("***BFS***");
        pb.printPlan(pb.bfs());
        System.out.println(pb.getBfsProbe());
    }

    private List<Variable> maladies;
    private List<Variable> syptms;
    private List<Action> medecine;
    private List<Action> experimentalMedecine;
    private final Action guerison;

    //Variables booléennes -> maladies
    static Variable angina = new Variable("ANGINA", "TRUE", "FALSE");
    static Variable flu = new Variable("FLU", "TRUE", "FALSE");
    static Variable pox = new Variable("POX", "TRUE", "FALSE");
    static Variable plague = new Variable("PLAGUE", "TRUE", "FALSE");

    //Variables à niveau -> symptômes
    static public Variable fever = new Variable("FEVER", "HIGH", "MEDIUM", "LOW", "NONE");
    static public Variable cough = new Variable("COUGH", "HIGH", "MEDIUM", "LOW", "NONE");
    static public Variable buttons = new Variable("BUTTONS", "HIGH", "MEDIUM", "LOW", "NONE");

    public HealthCare() {

        this.maladies = new ArrayList();
        this.maladies.add(angina);
        this.maladies.add(flu);
        this.maladies.add(pox);
        this.maladies.add(plague);

        this.syptms = new ArrayList();
        this.syptms.add(fever);
        this.syptms.add(cough);
        this.syptms.add(buttons);

        //Intialisation liste de medicaments
        this.medecine = new ArrayList();
        for (Variable var : this.syptms) {
            for (String val : var.getDomain()) {
                if (!(val.equals("NONE"))) {
                    String name = "SYRUP_" + var.getName() + "_" + val;
                    Action action = new Action(name);
                    ActionRule rule = new ActionRule();
                    rule.ajoutPrecondition(var, val);
                    String effet = "";
                    switch (val) {
                        case "HIGH":
                            effet = "MEDIUM";
                            break;
                        case "MEDIUM":
                            effet = "LOW";
                            break;
                        case "LOW":
                            effet = "NONE";
                            break;
                    }
                    rule.ajoutEffet(var, effet);
                    action.addRule(rule);
                    medecine.add(action);
                }
            }
        }

        this.guerison = new Action("GUERISON");
        for (Variable m : this.maladies) {
            ActionRule r = new ActionRule();
            for (Variable s : this.syptms) {
                r.ajoutPrecondition(s, "NONE");
            }
            r.ajoutEffet(m, "FALSE");
            this.guerison.addRule(r);
        }

        this.experimentalMedecine = new ArrayList();

    }

    public Action getGuerison() {
        return guerison;
    }

    public List<Action> getMedecine() {
        return medecine;
    }

    public Action getGuerison(Variable maladie) {
        ActionRule rule = new ActionRule();
        for (Variable sympt : this.syptms) {
            rule.ajoutPrecondition(sympt, "NONE");
        }
        rule.ajoutEffet(maladie, "FALSE");
        Action guerison = new Action();
        guerison.addRule(rule);
        return guerison;
    }

    public Action makeRandMedecine(Random r, int i) {

        Action medecine = new Action("MEDECINE_" + String.valueOf(i));

        //copie de la liste des symptomes dans symptCopy
        List<Variable> symptCopy = new ArrayList();
        symptCopy.addAll(this.syptms);
        //fin de la copie
        ActionRule rule = new ActionRule();
        rule.ajoutEffet(symptCopy.remove(r.nextInt(symptCopy.size())), "NONE");
        medecine.addRule(rule);

        for (Variable symptome : symptCopy) {
            rule = new ActionRule();
            //copie de la liste des valeurs dans domaineCopy
            List<String> domaineCopy = new ArrayList();
            domaineCopy.addAll(symptome.getDomain());
            domaineCopy.remove("NONE");
            //fin de la copie
            rule.ajoutEffet(symptome, domaineCopy.get(r.nextInt(domaineCopy.size())));
            medecine.addRule(rule);
        }

        return medecine;
    }

    public List<Action> makeNMedecine(int n, int seed) {

        Random r = new Random(seed);
        for (int i = 0; i < n; i++) {
            experimentalMedecine.add(makeRandMedecine(r, i));
        }
        return this.experimentalMedecine;
    }

    public State genInitialState(Random r) {
        State initialState = new State();

        int choixMaladie = r.nextInt(maladies.size());
        int nbSymptomes = r.nextInt(syptms.size());

        Variable maladie = maladies.get(choixMaladie);
        initialState.add(maladie, Variable.TRUE);

        List<Variable> symptomes = new ArrayList();
        symptomes.addAll(syptms);

        for (Variable symptome : symptomes) {
            int etatSymptome = r.nextInt(symptome.getDomain().size());
            List<String> sympDomain = new ArrayList();
            sympDomain.addAll(symptome.getDomain());
            initialState.add(symptome, sympDomain.get(etatSymptome));
        }
        return initialState;
    }
}
