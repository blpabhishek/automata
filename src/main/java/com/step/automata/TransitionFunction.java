package com.step.automata;


import java.util.HashMap;
import java.util.Map;

public class TransitionFunction {
    private Map<State, Transition> table = new HashMap<>();

    public void addTransition(State currentState, Transition transition) {
        table.put(currentState, transition);
    }

    public State apply(State currentState, char alphabet) {
        Transition transitions = getTransition(currentState);
        States nextStates = transitions.nextStates(alphabet);
        for (State nextState : nextStates) return nextState;
        return null;
    }

    public States apply(States currentState, char alphabet) {
        States states = new States();
        for (State state : currentState) {
            Transition transitions = getTransition(state);
            States nextStates = transitions.nextStates(alphabet);
            if (!nextStates.isEmpty()) {
                States epsilonClosure = getEpsilonClosure(state);
                states.addAll(epsilonClosure);
            }
            states.addAll(nextStates);
        }
        return states;
    }

    protected States getEpsilonClosure(State state) {
        States states = new States();
        Transition transitions = getTransition(state);
        if (transitions.hasEpsilonTransition()) {
            States epsilonTransition = transitions.getEpsilonTransition();
            for (State epsilonState : epsilonTransition) {
                States epsilonClosure = getEpsilonClosure(epsilonState);
                states.addAll(epsilonClosure);
                states.add(epsilonState);
                states.add(state);
            }
        }
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
