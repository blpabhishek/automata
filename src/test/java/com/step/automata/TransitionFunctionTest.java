package com.step.automata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransitionFunctionTest {
    @Test
    public void shouldBeAbleToAddATransitionAndGetBackTheNextStateFromTransitionTable() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transition = new Transition();
        transition.defineNextState('0', q2);
        transitionFunction.addTransition(q1, transition);

        State nextState = transitionFunction.apply(q1, '0');
        assertEquals(nextState, q2);
    }

    @Test
    public void shouldBeAbleToAddMultipleTransitionAndGetBackTheNextStateFromTransitionTable() {
        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");

        States currentStates = new States();
        currentStates.add(stateQ2);
        currentStates.add(stateQ4);

        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e', stateQ2);
        transitionQ1.defineNextState('e', stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ3);
        transitionQ2.defineNextState('1', stateQ2);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);

        States nextStates = transitionFunction.apply(currentStates, '0');

        States expected = new States();
        expected.add(stateQ3);

        assertEquals(expected, nextStates);
    }

    @Test
    public void shouldBeAbleToAddThreeTimesEpsilonTransitionAndGetBackTheNextStateFromTransitionTable() {
        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");
        State stateQ5 = new State("q5");

        States currentStates = new States();
        currentStates.add(stateQ4);
        currentStates.add(stateQ3);
        currentStates.add(stateQ2);

        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e', stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ3);
        transitionQ2.defineNextState('1', stateQ5);

        Transition transitionQ4 = new Transition();
        transitionQ4.defineNextState('e', stateQ3);
        transitionQ4.defineNextState('e', stateQ2);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);
        transitionFunction.addTransition(stateQ4, transitionQ4);

        States nextStates = transitionFunction.apply(currentStates, '1');

        States expected = new States();
        expected.add(stateQ5);

        assertEquals(expected, nextStates);
    }
}