package com.step.automata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFAMachineTest {
    private NFAMachine getNfaMachine() {
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
        transitionFunction.addTransition(stateQ1, '0', stateQ1);
        transitionFunction.addTransition(stateQ1, '1', stateQ1);
        transitionFunction.addTransition(stateQ1, '1', stateQ2);

        return new NFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    private NFAMachine getNfaMachineWithEpsilon() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");

        Set<State> setOfStates = new HashSet<>();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);
        setOfStates.add(stateQ3);
        setOfStates.add(stateQ4);

        Set<State> setOfFinalStates = new HashSet<>();
        setOfFinalStates.add(stateQ3);
        setOfFinalStates.add(stateQ4);

        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.addTransition(stateQ1, '0', stateQ2);
        transitionFunction.addTransition(stateQ1, 'e', stateQ3);
        transitionFunction.addTransition(stateQ2, '1', stateQ2);
        transitionFunction.addTransition(stateQ2, '1', stateQ4);
        transitionFunction.addTransition(stateQ3, 'e', stateQ2);
        transitionFunction.addTransition(stateQ3, '0', stateQ4);
        transitionFunction.addTransition(stateQ4, '0', stateQ3);

        return new NFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAString() {
        NFAMachine machine = getNfaMachine();
        assertTrue(machine.check("0001"));
        assertFalse(machine.check("0000"));
    }

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAStringWhenStateMachineHasEpsilon() {
        NFAMachine machine = getNfaMachineWithEpsilon();
        assertTrue(machine.check("000"));
    }
}
