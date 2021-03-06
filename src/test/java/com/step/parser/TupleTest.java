package com.step.parser;

import com.step.automata.State;
import com.step.automata.States;
import com.step.automata.Transition;
import com.step.automata.TransitionFunction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TupleTest {
    private Tuple tuple;

    @Before
    public void setUp() {
        String q0 = "q0";
        String q1 = "q1";
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");
        Set<String> setOfStates = new HashSet<>();
        setOfStates.add(q0);
        setOfStates.add(q1);
        Set<String> setOfFinalStates = new HashSet<>();
        setOfFinalStates.add(q0);

        Map<String, Map<String, Set<String>>> transitions = new HashMap<>();
        HashMap<String, Set<String>> subTransition = new HashMap<>();
        HashSet<String> q1Pos = new HashSet<>();
        q1Pos.add(q0);
        subTransition.put("0", q1Pos);
        HashSet<String> q2Pos = new HashSet<>();
        q2Pos.add(q1);
        subTransition.put("1", q2Pos);
        transitions.put(q0, subTransition);

        tuple = new Tuple(setOfStates, alphabetSet, q0, setOfFinalStates, transitions);
    }

    @Test
    public void getStates() {
        State q0 = new State("q0");
        State q1 = new State("q1");

        States setOfStates = new States();
        setOfStates.add(q0);
        setOfStates.add(q1);

        assertEquals(setOfStates, tuple.getStates());
    }

    @Test
    public void getAlphabets() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        Set<String> alphabets = tuple.getAlphabets();
        assertEquals(alphabets, alphabetSet);
    }

    @Test
    public void getInitialState() {
        assertEquals(tuple.getInitialState(), new State("q0"));
    }

    @Test
    public void getFinalStates() {
        State q0 = new State("q0");
        States setOfFinalStates = new States();
        setOfFinalStates.add(q0);

        assertEquals(tuple.getFinalStates(), setOfFinalStates);
    }

    @Test
    public void getTransitions() {
        State q0 = new State("q0");
        State q1 = new State("q1");
        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transition = new Transition();
        transition.defineNextState('0',q0);
        transition.defineNextState('1',q1);
        transitionFunction.addTransition(q0,transition);

        assertEquals(tuple.getTransitionsFunction(), transitionFunction);
    }

}