package com.step.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransitionFunction {
    private Map<State, Map<Character, State>> table = new HashMap<>();

    public void addTransition(State currentState, char alphabet, State nextState) {
        Map<Character, State> subTransition = table.get(currentState);
        if (subTransition == null) subTransition = new HashMap<>();
        subTransition.put(alphabet, nextState);
        table.put(currentState, subTransition);
    }

    public State apply(State currentState, char alphabet){
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
        for (State state : currentState) {
            Map<Character, State> transitions = table.get(state);
            if(transitions!= null && hasEpsilon(transitions)) {
                State e = transitions.get('e');
                Map<Character, State> transition = table.get(e);
                states.add(transition.get(alphabet));
            }
            State nextState;
            try {
                nextState = apply(state, alphabet);
                states.add(nextState);
            } catch (RuntimeException ignored){}
        }
        return states;
    }

    private boolean hasEpsilon(Map<Character, State> transitions) {
        return transitions.get('e')!=null;
    }

    @Override
    public String toString() {
        return String.format("TransitionFunction{%s}", table);
    }
}
