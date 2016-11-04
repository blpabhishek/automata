package com.step.automata;

import com.step.exceptions.IllegalAlphabetException;
import com.step.exceptions.IllegalStateException;

import java.util.Set;

public class DFAMachine {


    private final Set<String> alphabets;
    private final States allStates;
    private final States finalStates;
    private final TransitionFunction transitionFunction;
    private final State initialState;

    public DFAMachine(State initialState, TransitionFunction transitionFunction, States finalStates, States allStates, Set<String> alphabets) {
        this.transitionFunction = transitionFunction;
        this.finalStates = finalStates;
        this.allStates = allStates;
        this.alphabets = alphabets;
        this.initialState = initialState;
    }

    public boolean check(String string) {
        State currentState = initialState;
        for (int index = 0; index < string.length(); index++) {
            char alphabet = string.charAt(index);
            validate(alphabet);
            currentState = transitionFunction.apply(currentState, alphabet);
            validate(currentState);
        }
        return finalStates.contains(currentState);
    }

    private void validate(Character alphabet) {
        if (!alphabets.contains(alphabet.toString())) {
            throw new IllegalAlphabetException(alphabet);
        }
    }

    private void validate(State state) {
        if (!allStates.contains(state)) {
            throw new IllegalStateException(state);
        }
    }
}
