/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 21600639
 */
public class PlanningProblem {
    
    private State initialState;
    private Set<State> finalStates;
    private Set<Action> availableActions;

    public PlanningProblem(State initialState, Set<Action> availableActions) {
        this.initialState = initialState;
        this.availableActions = availableActions;
        this.finalStates = new HashSet<State>();
    }

    public State getInitialState() {
        return initialState;
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public Set<Action> getAvailableActions() {
        return availableActions;
    }
    
    
}
