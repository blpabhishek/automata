package com.step.automata;

import java.util.Set;

public class DFAMachine {


    private final Set<String> setOfAlphabets;
    private final Set<State> setOfStates;
    private final Set<State> setOfFinalStates;
    private final TransitionFunction transitionFunction;
    private State currentState;

    public DFAMachine(State initialState, TransitionFunction transitionFunction, Set<State> setOfFinalStates, Set<State> setOfStates, Set<String> setOfAlphabets) {
        this.transitionFunction = transitionFunction;
        this.setOfFinalStates = setOfFinalStates;
        this.setOfStates = setOfStates;
        this.setOfAlphabets = setOfAlphabets;
        this.currentState = initialState;
    }

    public boolean check(String string) {
        for (int index = 0; index < string.length(); index++) {
            char alphabet = string.charAt(index);
            validate(alphabet);
            currentState = transitionFunction.apply(currentState, alphabet);
        }
        return setOfFinalStates.contains(currentState);
    }

    private void validate(Character alphabet) {
        if(!setOfAlphabets.contains(alphabet.toString())){
            throw new IllegalAlphabetException(alphabet);
        }
    }
}
