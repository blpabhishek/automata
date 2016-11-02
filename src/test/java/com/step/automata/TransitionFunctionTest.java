package com.step.automata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TransitionFunctionTest {
    @Test
    public void shouldBeAbleToAddATransitionAndGetBackTheNextStateFromTransitionTable() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transition = new Transition();
        transition.defineNextState('0',q2);
        transitionFunction.addTransition(q1,transition);

        State nextState = transitionFunction.apply(q1, '0');
        assertEquals(nextState, q2);
    }
    @Test
    public void shouldBeAbleToAddMultipleTransitionAndGetBackTheNextStateFromTransitionTable() {
        State stateQ1 = new State("q1");
        State stateQ2 = new State("q2");
        State stateQ3 = new State("q3");
        State stateQ4 = new State("q4");

        Set<State> currentStates = new HashSet<>();
        currentStates.add(stateQ1);
        
        TransitionFunction transitionFunction = new TransitionFunction();
        Transition transitionQ1 = new Transition();
        transitionQ1.defineNextState('e',stateQ2);
        transitionQ1.defineNextState('e',stateQ4);

        Transition transitionQ2 = new Transition();
        transitionQ2.defineNextState('0', stateQ3);
        transitionQ2.defineNextState('1', stateQ2);

        transitionFunction.addTransition(stateQ1, transitionQ1);
        transitionFunction.addTransition(stateQ2, transitionQ2);

        System.out.println(transitionFunction);
        Set<State> nextStates = transitionFunction.apply(currentStates, 'e');
        System.out.println(nextStates);
    }
}