package com.step.parser;

import com.step.automata.*;

import java.util.*;

public class FATestRunner {
    private final String name;
    private final String type;
    private final Tuple tupleObject;
    private final FATestCases faTestCases;

    public FATestRunner(String name, String type, Tuple tupleObject, FATestCases faTestCases) {
        this.name = name;
        this.type = type;
        this.tupleObject = tupleObject;
        this.faTestCases = faTestCases;
    }

    public boolean validatePassCases() {
        DFAMachine dfaMachine = getDfaMachine();
        Set<String> passCases = faTestCases.getPassCases();
        for (String passCase : passCases) {
            if (!dfaMachine.check(passCase)) {
                return false;
            }
        }
        return true;
    }

    private Set<State> mapToState(Set<String> states) {
        Set<State> setOfStates = new HashSet<>();
        for (String state : states) {
            setOfStates.add(new State(state));
        }
        return setOfStates;
    }

    public boolean validateFailCases() {
        DFAMachine dfaMachine = getDfaMachine();
        Set<String> failCases = faTestCases.getFailCases();
        for (String failCase : failCases) {
            if (dfaMachine.check(failCase)) {
                return false;
            }
        }
        return true;
    }

    public DFAMachine getDfaMachine() {
        Set<String> alphabets = tupleObject.getAlphabets();
        State initialState = new State(tupleObject.getInitialState());
        Set<State> finalStates = mapToState(tupleObject.getFinalStates());
        Set<String> stringSet = tupleObject.getStates();
        Set<State> states = mapToState(stringSet);
        Map<String, Map<String, String>> transitions = tupleObject.getTransitions();
        TransitionFunction transitionFunction = new TransitionFunction();
        for (String state : stringSet) {
            Map<String, String> stringStringMap = transitions.get(state);
            for (String alphabet : alphabets) {
                String nextState = stringStringMap.get(alphabet);
                transitionFunction.addTransition(new State(state), alphabet.charAt(0), new State(nextState));
            }
        }
        return new DFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
    }
}
