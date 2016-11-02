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
        State initialState = tupleObject.getInitialState();
        Set<String> alphabets = tupleObject.getAlphabets();
        Set<State> finalStates = tupleObject.getFinalStates();
        Set<State> states = tupleObject.getStates();
        TransitionFunction transitionFunction = tupleObject.getTransitions();
        return new DFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
    }
}
