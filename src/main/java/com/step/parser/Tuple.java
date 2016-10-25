package com.step.parser;

import java.util.Map;
import java.util.Set;

public class Tuple {

    private final Set<String> states;
    private final Set<String> alphabets;
    private final String initialState;
    private final Set<String> finalStates;
    private final Map<String, Map<String, String>> transitions;

    public Tuple(Set<String> states, Set<String> alphabets, String initialState, Set<String> finalStates, Map<String, Map<String, String>> transitions) {
        this.states = states;
        this.alphabets = alphabets;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public Set<String> getStates() {
        return states;
    }

    public Set<String> getAlphabets() {
        return alphabets;
    }

    public String getInitialState() {
        return initialState;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }

    public Map<String, Map<String, String>> getTransitions() {
        return transitions;
    }
}
