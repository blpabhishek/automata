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
        Transition transition = new Transition();
        transition.defineNextState('0',stateQ1);
        transition.defineNextState('1',stateQ1);
        transition.defineNextState('1',stateQ2);
        transitionFunction.addTransition(stateQ1, transition);

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

        Set<State> setOfStates = new HashSet<>();
        setOfStates.add(stateQ1);
        setOfStates.add(stateQ2);
        setOfStates.add(stateQ3);
        setOfStates.add(stateQ4);
        setOfStates.add(stateQ5);

        Set<State> setOfFinalStates = new HashSet<>();
        setOfFinalStates.add(stateQ2);
        setOfFinalStates.add(stateQ4);

        TransitionFunction transitionFunction = new TransitionFunction();

        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e', stateQ2);
        transitionQ1.defineNextState('e', stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ3);
        transitionQ2.defineNextState( '1', stateQ2);

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

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAString() {
        NFAMachine machine = getNfaMachine();
        assertTrue(machine.check("0000"));
        assertFalse(machine.check("0001"));
    }

    @Test
    public void shouldBeAbleToRunANFAMachineAndAcceptAStringWhenStateMachineHasEpsilon() {
        NFAMachine machine = getDFAForEitherEvenNumberOfZeroesOrEvenNumberOfOnes();
        assertTrue(machine.check("00"));
    }
}
