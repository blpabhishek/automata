package com.step.automata;

import java.util.HashSet;
import java.util.Set;

public class NFAMachine {
    private final State initialState;
    private final TransitionFunction transitionFunction;
    private final Set<State> setOfFinalStates;
    private final Set<State> setOfStates;
    private final Set<String> alphabetSet;

    public NFAMachine(State initialState, TransitionFunction transitionFunction, Set<State> setOfFinalStates, Set<State> setOfStates, Set<String> alphabetSet) {

        this.initialState = initialState;
        this.transitionFunction = transitionFunction;
        this.setOfFinalStates = setOfFinalStates;
        this.setOfStates = setOfStates;
        this.alphabetSet = alphabetSet;
    }

    public boolean check(String string) {
        Set<State> currentState = new HashSet<>();
        currentState.add(initialState);
        for (int index = 0; index < string.length(); index++) {
            char alphabet = string.charAt(index);
            //validate(alphabet);
            currentState = transitionFunction.apply(currentState, alphabet);
            //validate(currentState);
        }
        return isOnFinalState(currentState, setOfFinalStates);
    }

    private boolean isOnFinalState(Set<State> currentState, Set<State> setOfFinalStates) {
        for (State finalState : setOfFinalStates) {
            if (currentState.contains(finalState)) {
                return true;
            }
        }
        return false;
    }
}
