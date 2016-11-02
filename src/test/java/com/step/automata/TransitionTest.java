package com.step.automata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TransitionTest {
    @Test
    public void shouldBeAbleToDefineMultipleTransitionsOnSingleAlphabet() {
        Transition transition = new Transition();
        State q1 = new State("q1");
        transition.defineNextState('e', q1);
        State q2 = new State("q2");
        transition.defineNextState('e', q2);
        transition.defineNextState('0', q2);

        HashSet<State> set = new HashSet<>();
        set.add(q1);
        set.add(q2);

        Set<State> stateSet = transition.nextStates('e');
        assertEquals(set, stateSet);
    }
}
