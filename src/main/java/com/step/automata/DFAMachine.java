package com.step.automata;

import com.step.exceptions.IllegalAlphabetException;
import com.step.exceptions.IllegalStateException;

import java.util.Set;

public class DFAMachine {


    private final Set<String> setOfAlphabets;
    private final Set<State> setOfStates;
    private final Set<State> setOfFinalStates;
    private final TransitionFunction transitionFunction;
    private final State initialState;

    public DFAMachine(State initialState, TransitionFunction transitionFunction, Set<State> setOfFinalStates, Set<State> setOfStates, Set<String> setOfAlphabets) {
        this.transitionFunction = transitionFunction;
        this.setOfFinalStates = setOfFinalStates;
        this.setOfStates = setOfStates;
        this.setOfAlphabets = setOfAlphabets;
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
        return setOfFinalStates.contains(currentState);
    }

    private void validate(Character alphabet) {
        if (!setOfAlphabets.contains(alphabet.toString())) {
            throw new IllegalAlphabetException(alphabet);
        }
    }

    private void validate(State state) {
        if (!setOfStates.contains(state)) {
            throw new IllegalStateException(state);
        }
    }
}
