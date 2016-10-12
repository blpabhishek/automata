package com.step.automata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFAMachineTest {

    private DFAMachine getDfaMachine() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        Set<State> setOfStates = new HashSet<>();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);

        Set<State> setOfFinalStates = new HashSet<>();
        setOfFinalStates.add(stateQ2);

        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.addTransition(new Transition(stateQ1, '0', stateQ2));
        transitionFunction.addTransition(new Transition(stateQ1, '1', stateQ1));
        transitionFunction.addTransition(new Transition(stateQ2, '0', stateQ2));
        transitionFunction.addTransition(new Transition(stateQ2, '1', stateQ1));

        return new DFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    @Test
    public void shouldBeAbleToRunADFAMachineAndAcceptAString() {
        DFAMachine machine = getDfaMachine();
        assertTrue(machine.check("0000"));
    }

    @Test
    public void shouldBeAbleToRunADFAMachineAndRejectAString() {
        DFAMachine machine = getDfaMachine();
        assertFalse(machine.check("0001"));
    }
}