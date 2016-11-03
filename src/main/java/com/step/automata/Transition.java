package com.step.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Transition {
    private final Map<Character, Set<State>> transitions = new HashMap<>();
    private Character epsilon = 'e';

    public void defineNextState(char alphabet, State state) {
        Set<State> stateSet = transitions.get(alphabet);
        if (!transitions.containsKey(alphabet)) stateSet = new HashSet<>();
        stateSet.add(state);
        transitions.put(alphabet, stateSet);
    }

    public Set<State> nextStates(char alphabet) {
        if (transitions.containsKey(alphabet))
            return transitions.get(alphabet);
        return new HashSet<>();
    }

    public boolean hasEpsilonTransition() {
        return transitions.containsKey(epsilon);
    }

    public Set<State> getEpsilonTransition() {
        if (transitions.containsKey(epsilon))
            return transitions.get(epsilon);
        return new HashSet<>();
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
