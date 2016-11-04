package com.step.automata;

import java.util.HashSet;

public class States extends HashSet<State> {
    public States intersection(States currentState) {
        States states = new States();
        for (State state : currentState) {
            if (this.contains(state)) {
                states.add(state);
            }
        }
        return states;
    }
}
