package com.step.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransitionFunction {
    private Map<State, Map<Character, State>> table = new HashMap<>();

    public void addTransition(Transition transition) {
        State state = transition.currentState();
        char alphabet = transition.getAlphabet();
        Map<Character, State> subTransition = table.get(state);
        if (subTransition == null) subTransition = new HashMap<>();
        subTransition.put(alphabet, transition.nextState(alphabet));
        table.put(state, subTransition);
    }

    public State apply(State currentState, char alphabet) {
        State state;
        Map<Character, State> transitions = table.get(currentState);
        if (transitions == null)
            throw new RuntimeException("Transitions Not Defined for :" + currentState);
        state = transitions.get(alphabet);
        if (state == null)
            throw new RuntimeException("Transitions Not Defined on " + currentState + "for alphabet" + alphabet);
        return state;
    }

    public Set<State> apply(Set<State> currentState, char alphabet) {
        Set<State> states = new HashSet<>();

        return states;
    }

    @Override
    public String toString() {
        return String.format("TransitionFunction{%s}", table);
    }
}
