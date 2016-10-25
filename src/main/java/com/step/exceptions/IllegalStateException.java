package com.step.exceptions;

import com.step.automata.State;

public class IllegalStateException extends RuntimeException {
    public IllegalStateException(State state) {
        super("Illegal State " + state + "Exception ");
    }
}
