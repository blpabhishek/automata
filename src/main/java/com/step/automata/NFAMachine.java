package com.step.automata;

import com.step.exceptions.IllegalAlphabetException;
import com.step.exceptions.IllegalStateException;

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
        States currentStates = transitionFunction.resolveEpsilonTransition(initialState);
        for (int index = 0; index < string.length(); index++) {
            char alphabet = string.charAt(index);
            validate(alphabet);
            currentStates = transitionFunction.apply(currentStates, alphabet);
            validate(currentStates);
        }
        return isOnFinalState(currentStates);
    }

    private void validate(States currentStates) {
        for (State state : currentStates) {
            if (!allStates.contains(state))
                throw new IllegalStateException(state);
        }
    }

    private void validate(Character alphabet) {
        if (!alphabets.contains(alphabet.toString())) {
            throw new IllegalAlphabetException(alphabet);
        }
    }

    private boolean isOnFinalState(States currentState) {
        return !finalStates.intersection(currentState).isEmpty();
    }
}
