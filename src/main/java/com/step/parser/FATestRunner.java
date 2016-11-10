package com.step.parser;

import com.step.automata.*;

import java.util.Set;

public class FATestRunner {
    private final String name;
    private final String type;
    private final Tuple tuple;
    private final FATestCases faTestCases;
    private final String DFA = "dfa";

    public FATestRunner(String name, String type, Tuple tuple, FATestCases faTestCases) {
        this.name = name;
        this.type = type;
        this.tuple = tuple;
        this.faTestCases = faTestCases;
    }

    public boolean validatePassCases() {
        Machine dfaMachine = getMachine();
        Set<String> passCases = faTestCases.getPassCases();
        for (String passCase : passCases) {
            if (!dfaMachine.check(passCase)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFailCases() {
        Machine dfaMachine = getMachine();
        Set<String> failCases = faTestCases.getFailCases();
        for (String failCase : failCases) {
            if (dfaMachine.check(failCase)) {
                return false;
            }
        }
        return true;
    }

    public Machine getMachine() {
        State initialState = tuple.getInitialState();
        Set<String> alphabets = tuple.getAlphabets();
        States finalStates = tuple.getFinalStates();
        States states = tuple.getStates();
        TransitionFunction transitionFunction = tuple.getTransitions();
        if(type.equals(DFA)) return new DFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
        return new NFAMachine(initialState, transitionFunction, finalStates, states, alphabets);

    }
}
