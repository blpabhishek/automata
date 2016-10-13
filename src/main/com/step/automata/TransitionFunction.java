package com.step.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransitionFunction {
    private Map<State, List<Transition>> table = new HashMap<>();

    public void addTransition(Transition transition) {
        State state = transition.currentState();
        List<Transition> transitions = table.get(state);
        if (transitions == null) transitions = new ArrayList<>();
        transitions.add(transition);
        table.put(state, transitions);
    }

    public State apply(State currentState, char alphabet) {
        State state = null;
        List<Transition> transitions = table.get(currentState);
        if (transitions == null)
            throw new RuntimeException("Transitions Not Defined for this State");
        for (Transition transition : transitions) {
                state = transition.nextState(alphabet);
                break;
        }
        return state;
    }
}
