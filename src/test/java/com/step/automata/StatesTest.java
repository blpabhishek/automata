package com.step.automata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatesTest {
    @Test
    public void setsIsSetOfSateWhereItDoesNotAllowDuplication() {
        State q1 = new State("q1");
        States states = new States();
        states.add(q1);
        states.add(q1);

        assertTrue(states.contains(q1));

        assertEquals(states.size(), 1);
    }

    @Test
    public void getTheIntersectionOfTwoStatesTreatingThemAsSet() {
        State q1 = new State("q1");
        State q2 = new State("q2");

        States states1 = new States();
        states1.add(q1);
        states1.add(q2);

        States states2 = new States();
        states2.add(q2);

        States expected = new States();
        expected.add(q2);

        States intersection = states1.intersection(states2);
        assertEquals(expected, intersection);
    }
}
