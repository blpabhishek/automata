package com.step.automata;

public class IllegalAlphabetException extends RuntimeException {
    public IllegalAlphabetException(char message) {
        super("Alphabet " + message + " does not belongs to alphabet set");
    }
}
