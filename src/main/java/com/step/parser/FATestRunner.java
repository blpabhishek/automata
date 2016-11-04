package com.step.parser;

import com.step.automata.DFAMachine;
import com.step.automata.State;
import com.step.automata.States;
import com.step.automata.TransitionFunction;

import java.util.Set;

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
        States finalStates = tupleObject.getFinalStates();
        States states = tupleObject.getStates();
        TransitionFunction transitionFunction = tupleObject.getTransitions();
        return new DFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
    }
}
