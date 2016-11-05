package com.step.automata;

import java.util.HashMap;
import java.util.Map;

public class Transition {
    private final Map<Character, States> transitions = new HashMap<>();
    private final Character EPSILON = 'e';

    public void defineNextState(char alphabet, State state) {
        States stateSet = transitions.get(alphabet);
        if (!transitions.containsKey(alphabet)) stateSet = new States();
        stateSet.add(state);
        transitions.put(alphabet, stateSet);
    }

    public States nextStates(char alphabet) {
        if (transitions.containsKey(alphabet))
            return transitions.get(alphabet);
        return new States();
    }

    public boolean hasEpsilonTransition() {
        return transitions.containsKey(EPSILON);
    }

    public States getEpsilonTransition() {
        if (transitions.containsKey(EPSILON))
            return transitions.get(EPSILON);
        return new States();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transition that = (Transition) o;

        return transitions.equals(that.transitions);

    }

    @Override
    public int hashCode() {
        return transitions.hashCode();
    }
}
