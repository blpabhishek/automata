package com.step.automata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFAMachineTest {
    private NFAMachine getNfaMachineForOddNoOfZeroes() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        States setOfStates = new States();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);

        States setOfFinalStates = new States();
        setOfFinalStates.add(stateQ2);

        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('0', stateQ2);
        transitionQ1.defineNextState('1', stateQ1);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ1);
        transitionQ2.defineNextState('1', stateQ2);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);

        return new NFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    private NFAMachine getDFAForEitherEvenNumberOfZeroesOrEvenNumberOfOnes() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");
        State stateQ5 = new State("q5");

        States setOfStates = new States();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);
        setOfStates.add(stateQ3);
        setOfStates.add(stateQ4);
        setOfStates.add(stateQ5);

        States setOfFinalStates = new States();
        setOfFinalStates.add(stateQ2);
        setOfFinalStates.add(stateQ4);

        TransitionFunction transitionFunction = new TransitionFunction();

        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e', stateQ2);
        transitionQ1.defineNextState('e', stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ3);
        transitionQ2.defineNextState('1', stateQ2);

        Transition transitionQ3 = new Transition();
        transitionQ3.defineNextState('0', stateQ2);
        transitionQ3.defineNextState('1', stateQ3);

        Transition transitionQ4 = new Transition();
        transitionQ4.defineNextState('0', stateQ4);
        transitionQ4.defineNextState('1', stateQ5);

        Transition transitionQ5 = new Transition();
        transitionQ5.defineNextState('0', stateQ5);
        transitionQ5.defineNextState('1', stateQ4);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);
        transitionFunction.addTransition(stateQ3, transitionQ3);
        transitionFunction.addTransition(stateQ4, transitionQ4);
        transitionFunction.addTransition(stateQ5, transitionQ5);

        return new NFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    private NFAMachine getDFAFor0_1_or1_0_WithExtraEpsilonsWhere_RepresentsACleanStar() {
        Set<String> alphabetSet = new HashSet<>();
        alphabetSet.add("0");
        alphabetSet.add("1");

        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");
        State stateQ5 = new State("q5");
        State stateQ6 = new State("q6");
        State stateQ7 = new State("q7");

        States setOfStates = new States();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);
        setOfStates.add(stateQ3);
        setOfStates.add(stateQ4);
        setOfStates.add(stateQ5);
        setOfStates.add(stateQ6);
        setOfStates.add(stateQ7);

        States setOfFinalStates = new States();
        setOfFinalStates.add(stateQ7);
        setOfFinalStates.add(stateQ6);

        TransitionFunction transitionFunction = new TransitionFunction();

        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e', stateQ2);
        transitionQ1.defineNextState('e', stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ2);
        transitionQ2.defineNextState('e', stateQ3);

        Transition transitionQ3 = new Transition();
        transitionQ3.defineNextState('1', stateQ3);
        transitionQ3.defineNextState('e', stateQ6);

        Transition transitionQ4 = new Transition();
        transitionQ4.defineNextState('1', stateQ4);
        transitionQ4.defineNextState('e', stateQ5);

        Transition transitionQ5 = new Transition();
        transitionQ5.defineNextState('0', stateQ5);
        transitionQ5.defineNextState('e', stateQ7);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);
        transitionFunction.addTransition(stateQ3, transitionQ3);
        transitionFunction.addTransition(stateQ4, transitionQ4);
        transitionFunction.addTransition(stateQ5, transitionQ5);

        return new NFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAString() {
        NFAMachine machine = getNfaMachineForOddNoOfZeroes();

        assertTrue(machine.check("0"));
        assertTrue(machine.check("000"));
        assertTrue(machine.check("00000"));
        assertTrue(machine.check("10"));
        assertTrue(machine.check("101010"));
        assertTrue(machine.check("010101"));

        assertFalse(machine.check("00"));
        assertFalse(machine.check("0000"));
        assertFalse(machine.check("1001"));
        assertFalse(machine.check("1010"));
        assertFalse(machine.check("001100"));

    }

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAStringWhenStateMachineHasEpsilon() {
        NFAMachine machine = getDFAForEitherEvenNumberOfZeroesOrEvenNumberOfOnes();
        assertTrue(machine.check("000"));
        assertTrue(machine.check("0000"));
        assertTrue(machine.check("0101010"));
        assertTrue(machine.check("00010"));
        assertTrue(machine.check("11"));
        assertTrue(machine.check("1111"));
        assertTrue(machine.check("110101"));
        assertTrue(machine.check("10101010"));

        assertFalse(machine.check("0001"));
        assertFalse(machine.check("1110"));
        assertFalse(machine.check("111000"));
        assertFalse(machine.check("01"));
        assertFalse(machine.check("10"));
        assertFalse(machine.check("000111"));
    }

    @Test
    public void shouldBeAbleToRecognizeTheStringWithMultipleEpsilons() {
        NFAMachine machine = getDFAFor0_1_or1_0_WithExtraEpsilonsWhere_RepresentsACleanStar();
        assertTrue(machine.check(""));
        assertTrue(machine.check("0"));
        assertTrue(machine.check("1"));
        assertTrue(machine.check("00"));
        assertTrue(machine.check("11"));
        assertTrue(machine.check("001"));
        assertTrue(machine.check("110"));
        assertTrue(machine.check("011"));
        assertTrue(machine.check("100"));
        assertTrue(machine.check("0011"));
        assertTrue(machine.check("1100"));

        assertFalse(machine.check("101"));
        assertFalse(machine.check("010"));
        assertFalse(machine.check("11001"));
        assertFalse(machine.check("00110"));
        assertFalse(machine.check("0101"));
        assertFalse(machine.check("1010"));
    }
}
