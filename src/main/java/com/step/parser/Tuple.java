package com.step.parser;

import com.step.automata.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tuple {

    private final Set<String> states;
    private final Set<String> alphabets;
    private final String initialState;
    private final Set<String> finalStates;
    private final Map<String, Map<String, Set<String>>> transitionsFunction;
    private final String DFA = "dfa";

    public Tuple(Set<String> states, Set<String> alphabets, String initialState, Set<String> finalStates, Map<String, Map<String, Set<String>>> transitionsFunction) {
        this.states = states;
        this.alphabets = alphabets;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitionsFunction = transitionsFunction;
    }

    protected States getStates() {
        return mapToState(states);
    }

    protected Set<String> getAlphabets() {
        return alphabets;
    }

    protected State getInitialState() {
        return new State(initialState);
    }

    protected States getFinalStates() {
        return mapToState(finalStates);
    }

    public TransitionFunction getTransitionsFunction() {
        TransitionFunction transitionFunction = new TransitionFunction();
        for (String state : states) {
            Map<String, Set<String>> transitionFun = transitionsFunction.get(state);
            if (transitionFun != null) {
                Transition transition = getTransition(transitionFun);
                transitionFunction.addTransition(new State(state), transition);
            }
        }
        return transitionFunction;
    }

    private Transition getTransition(Map<String, Set<String>> transitionFunction) {
        Transition transition = new Transition();
        HashSet<String> alphabetSetWithEpsilon = new HashSet<>();
        alphabetSetWithEpsilon.addAll(alphabets);
        alphabetSetWithEpsilon.add("e");
        for (String alphabet : alphabetSetWithEpsilon) {
            Set<String> nextState = transitionFunction.containsKey(alphabet) ? transitionFunction.get(alphabet) : new HashSet<String>();
            for (String state : nextState) {
                transition.defineNextState(alphabet.charAt(0), new State(state));
            }
        }
        return transition;
    }

    private States mapToState(Set<String> states) {
        States setOfStates = new States();
        for (String state : states) {
            setOfStates.add(new State(state));
        }
        return setOfStates;
    }

    public Machine getMachine(String type) {
        State initialState = getInitialState();
        Set<String> alphabets = getAlphabets();
        States finalStates = getFinalStates();
        States states = getStates();
        TransitionFunction transitionFunction = getTransitionsFunction();
        if (type.equals(DFA)) return new DFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
        return new NFAMachine(initialState, transitionFunction, finalStates, states, alphabets);
    }
}
