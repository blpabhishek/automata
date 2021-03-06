package com.step.automata;

import com.step.exceptions.IllegalAlphabetException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFAMachineTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Machine getDfaMachine() {
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
        Transition transition = new Transition();
        transition.defineNextState('0', stateQ2);
        transition.defineNextState('1', stateQ1);
        transitionFunction.addTransition(stateQ1, transition);
        transitionFunction.addTransition(stateQ2, transition);

        return new DFAMachine(stateQ1, transitionFunction, setOfFinalStates, setOfStates, alphabetSet);
    }

    @Test
    public void shouldBeAbleToRunADFAMachineAndAcceptAString() {
        Machine machine = getDfaMachine();
        assertTrue(machine.check("0000"));
    }

    @Test
    public void shouldBeAbleToRunADFAMachineAndRejectAString() {
        Machine machine = getDfaMachine();
        assertFalse(machine.check("0001"));
    }

    @Test
    public void shouldNotAbleToRecognizeAStringWhichContainsAlphabetWhichIsNotInAlphabetSet() {
        Machine machine = getDfaMachine();
        exception.expect(IllegalAlphabetException.class);
        exception.expectMessage("Alphabet 2 does not belongs to alphabet set");
        assertFalse(machine.check("0021"));
    }
}
