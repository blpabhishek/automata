package com.step.automata;

import java.util.ArrayList;
import java.util.List;

public class TransitionFunction {
    private List<Transition> table = new ArrayList<>();

    public void addTransition(Transition transition) {
        table.add(transition);
    }

    public State apply(State currentState, char alphabet) {
        State state = null;
        for (Transition transition : table) {
            if (transition.isDefinedAt(currentState, alphabet)) //TODO:-Need a better way to get Next State
                state = transition.nextState(currentState, alphabet);
        }
        return state;
    }
}
