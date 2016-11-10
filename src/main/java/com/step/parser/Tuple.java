package com.step.parser;

import com.step.automata.State;
import com.step.automata.States;
import com.step.automata.Transition;
import com.step.automata.TransitionFunction;

import java.util.Map;
import java.util.Set;

public class Tuple {

    private final Set<String> states;
    private final Set<String> alphabets;
    private final String initialState;
    private final Set<String> finalStates;
    private final Map<String, Map<String, String>> transitionsFunction;

    public Tuple(Set<String> states, Set<String> alphabets, String initialState, Set<String> finalStates, Map<String, Map<String, String>> transitionsFunction) {
        this.states = states;
        this.alphabets = alphabets;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitionsFunction = transitionsFunction;
    }

    public States getStates() {
        return mapToState(states);
    }

    public Set<String> getAlphabets() {
        return alphabets;
    }

    public State getInitialState() {
        return new State(initialState);
    }

    public States getFinalStates() {
        return mapToState(finalStates);
    }

    public TransitionFunction getTransitionsFunction() {
        TransitionFunction transitionFunction = new TransitionFunction();
        for (String state : states) {
            Map<String, String> transitionFun = transitionsFunction.get(state);
            if(transitionFun!= null) {
                Transition transition = getTransition(transitionFun);
                transitionFunction.addTransition(new State(state), transition);
            }
        }
        return transitionFunction;
    }

    private Transition getTransition(Map<String, String> transitionFunction) {
        Transition transition = new Transition();
        for (String alphabet : alphabets) {
            String nextState = transitionFunction.get(alphabet);
            if(nextState!=null) {
                transition.defineNextState(alphabet.charAt(0), new State(nextState));
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
}
