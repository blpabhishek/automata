package com.step.automata;

import java.util.*;

public class TransitionFunction {
    private Map<State, Transition> table = new HashMap<>();

    public void addTransition(State currentState, Transition transition) {
        table.put(currentState, transition);
    }

    public State apply(State currentState, char alphabet) {
        Transition transitions = table.get(currentState);
        if (transitions == null)
            throw new RuntimeException("Transitions Not Defined for :" + currentState);
        Set<State> nextStates = transitions.nextStates(alphabet);
        if (nextStates.size() != 1)
            throw new RuntimeException("Transitions Not Defined for :" + currentState);
        for (State nextState : nextStates) {
            return nextState;
        }
        return null;
    }

    public Set<State> apply(Set<State> currentState, char alphabet) {
        Set<State> states = new HashSet<>();
        for (State state : currentState) {
            Transition transitions = table.get(state);
            Set<State> nextStates = transitions.nextStates(alphabet);
            states.addAll(nextStates);
        }
        return states;
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
