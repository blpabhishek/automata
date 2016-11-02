package com.step.parser;

import com.step.automata.State;
import com.step.automata.Transition;
import com.step.automata.TransitionFunction;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tuple {

    private final Set<String> states;
    private final Set<String> alphabets;
    private final String initialState;
    private final Set<String> finalStates;
    private final Map<String, Map<String, String>> transitions;

    public Tuple(Set<String> states, Set<String> alphabets, String initialState, Set<String> finalStates, Map<String, Map<String, String>> transitions) {
        this.states = states;
        this.alphabets = alphabets;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public Set<State> getStates() {
        return mapToState(states);
    }

    public Set<String> getAlphabets() {
        return alphabets;
    }

    public State getInitialState() {
        return new State(initialState);
    }

    public Set<State> getFinalStates() {
        return mapToState(finalStates);
    }

    public TransitionFunction getTransitions() {
        TransitionFunction transitionFunction = new TransitionFunction();
        for (String state : states) {
            Map<String, String> subTransition = transitions.get(state);
            if(subTransition!= null) {
                Transition transition = new Transition();
                for (String alphabet : alphabets) {
                    String nextState = subTransition.get(alphabet);
                    transition.defineNextState(alphabet.charAt(0), new State(nextState));
                }
                transitionFunction.addTransition(new State(state), transition);
            }
        }
        return transitionFunction;
    }

    private Set<State> mapToState(Set<String> states) {
        Set<State> setOfStates = new HashSet<>();
        for (String state : states) {
            setOfStates.add(new State(state));
        }
        return setOfStates;
    }
}
