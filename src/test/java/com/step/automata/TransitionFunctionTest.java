package com.step.automata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransitionFunctionTest {
    @Test
    public void shouldBeAbleToDefineATransitionTable() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        Transition transition = new Transition(q1, '0', q2);
        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.addTransition(transition);

        State nextState = transitionFunction.apply(q1, '0');
        assertEquals(nextState, q2);
    }
}