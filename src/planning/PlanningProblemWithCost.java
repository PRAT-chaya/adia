package planning;
import examples.HealthCare;

public class PlanningProblemWithCost implements PlanningProblem {
    public void function get-dijkstra-plan(Map <String, Integer> father, Integer plan, Map <Integer, State> goals, Map <String, Integer> distance) {
        Map <String, Integer> distance = new HashMap();
        Map <String, Integer> father = new HashMap();
        Map <String, Integer> plans = new HashMap();
        integer goals = new HashMap();

        Map <Integer, State> open = new HashMap();

        distance[this.getInitialState] = 0;
        father[this.getInitialState] = 0;

        while(open != 0) {
            int minKey = open.keys().stream().min(Integer::compare).get(); // On récupère la clé minimale dans notre HashSet
            State state = open.get(minKey); // 6: state ← argminnode∈open(distance(node))
            String minGoal = map.keySet().stream().findFirst().get();

            open.remove(minKey); // Suppression de notre clé courante des ouverts

            if(!state.satisfies(goal.get(minGoal).getAffectation())) { //8: if satises(state, problem.goal) then
                //9: goals ← goals ∪ {state}
                goals.remove(minGoal);
            }

            for(Action action : this.getActions()) {
                if(action.is_applicable(state)) {
                    State nextState = state.apply(action);

                    if(!distance.constainsKey(state)) {
                        distance[nextState] = 99999; // L'infini :->
                    }

                    if(distance[nextState] > distance[nextState] + action.getCost()) {
                        distance[nextState] = distance[state] + action.getCost();
                        father[nextState] = state;
                        plan[nextState] = action;
                        open.put(nextState);
                    }
                }
            }
        }

        return get-dijkstra-plan(father, plans, goals, distance);

        /*distance ← MAP(∅), father ← MAP(∅), plan ← MAP(∅), goals ← ∅
        2: open ← {problem.initial_state}
        3: distance[problem.initial_state] ← 0
        4: father[problem.initial_state] ← ∅
        5: while open 6= ∅ do
        6: state ← argminnode∈open(distance(node))
        7: open ← open \ {state}
        8: if satises(state, problem.goal) then
        9: goals ← goals ∪ {state}
        10: end if
        11: for all action ∈ problem.actions do
            12: if is-applicable(action, state) then
        13: next ← apply(action, state)
        14: if not next ∈ distance then
        15: distance[next] ← +∞
        16: end if
        17: if distance[next] > distance[state] + cost(action) then
        18: distance[next] ← distance[state] + cost(action)
        19: father[next] ← state
        20: plan[next] ← action
        21: open ← open ∪ {next}
        22: end if
        23: end if
        24: end for
        25: end while
        26: return get-dijkstra-plan(father, plan, goals, distance)*/
    }

    public void get-bfs-plan() {

        //1: father ← MAP(∅), plan ← MAP(∅), closed ← ∅
        //2: open ← {problem.initial_state}
        //: father[problem.initial_state] ← ∅

        while() { // while open = ∅ do
            // state ← dequeue(open)
            // closed ← closed ∪ state

            for() { // for all action ∈ problem.actions do
                if() { // if is-applicable(action, state) then
                    // next ← apply(action, state)
                    if() { // if not next ∈ closed and not next ∈ open then
                        // father[next] ← state
                        // plan[next] ← action
                        if() { // 13: if satises(next, problem.goal) then

                        }
                        else {
                            // 16: enqueue(open, next)
                        }
                    }
                }

            }
        }

        return 0;
    }

    public int function aStar() {
        while() { // while open = ∅ do
            // state ← argminnode∈open(value(node))
            if() { // if satisfies(state, problem.goal) then
                // return get-bfs-plan(father, plan, state)
            }
            else {
                // open ← open \ {state}
                for() { // for all action ∈ problem.actions do
                    if() { // if is-applicable(action, state) then
                        // 12: next ← apply(action, state)
                        if() { // if not next ∈ distance then
                            // distance[next] ← +∞
                        }
                        if() { // if distance[next] > distance[state] + cost(action) then
                            // 17: distance[next] ← distance[state] + cost(action)
                            // value[next] ← distance[next] + heuristic(next)
                            // father[next] ← state, action[next] ← action
                            // open ← open ∪ {next}
                        }
                    }
                }
            }

            return 0;
        }
    }
}