package com.step.automata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransitionTest {
    @Test
    public void shouldBeAbleToGiveNextState() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        Transition transition = new Transition(q1, '0', q2);

        State nextState = transition.nextState(q1, '0');
        assertEquals(nextState,q2);
    }
}