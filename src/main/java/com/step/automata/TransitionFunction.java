package com.step.automata;

import java.util.*;

public class TransitionFunction {
    private Map<State, Transition> table = new HashMap<>();

    public void addTransition(State currentState, Transition transition) {
        table.put(currentState, transition);
    }

    public State apply(State currentState, char alphabet) {
        Transition transitions = getTransition(currentState);
        Set<State> nextStates = transitions.nextStates(alphabet);
        for (State nextState : nextStates) return nextState;
        return null;
    }

    public Set<State> apply(Set<State> currentState, char alphabet) {
        Set<State> states = new HashSet<>();
        Set<State> updatedCurrentStates = resolveEpsilonTransition(currentState);
        for (State state : updatedCurrentStates) {
            Transition transitions = getTransition(state);
            Set<State> nextStates = transitions.nextStates(alphabet);
            states.addAll(nextStates);
        }
        return states;
    }

    private Set<State> resolveEpsilonTransition(Set<State> currentState) {
        Set<State> states = new HashSet<>();
        for (State state : currentState) {
            Transition transitions = getTransition(state);
            if (transitions.hasEpsilonTransition()) {
                Set<State> epsilonTransition = transitions.getEpsilonTransition();
                Set<State> stateSet = resolveEpsilonTransition(epsilonTransition);
                states.addAll(stateSet);
            }
        }
        states.addAll(currentState);
        return states;
    }

    private Transition getTransition(State state) {
        if (table.containsKey(state))
            return table.get(state);
        return new Transition();
    }

    @Override
    public String toString() {
        return String.format("TransitionFunction{%s}", table);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransitionFunction that = (TransitionFunction) o;

        return table != null ? table.equals(that.table) : that.table == null;

    }

    @Override
    public int hashCode() {
        return table != null ? table.hashCode() : 0;
    }
}
