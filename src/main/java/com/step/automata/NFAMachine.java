package com.step.automata;

import java.util.Set;

public class NFAMachine {
    private final Set<String> alphabets;
    private final States allStates;
    private final States finalStates;
    private final TransitionFunction transitionFunction;
    private final State initialState;

    public NFAMachine(State initialState, TransitionFunction transitionFunction, States finalStates, States allStates, Set<String> alphabets) {
        this.transitionFunction = transitionFunction;
        this.finalStates = finalStates;
        this.allStates = allStates;
        this.alphabets = alphabets;
        this.initialState = initialState;
    }

    public boolean check(String string) {
        States currentState = new States();
        currentState.add(initialState);
        for (int index = 0; index < string.length(); index++) {
            char alphabet = string.charAt(index);
            //validate(alphabet);
            currentState = transitionFunction.apply(currentState, alphabet);
            //validate(currentState);
        }
        return isOnFinalState(currentState);
    }

    private boolean isOnFinalState(States currentState) {
        return !finalStates.intersection(currentState).isEmpty();
    }
}
