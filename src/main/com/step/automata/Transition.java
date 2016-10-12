package com.step.automata;

public class Transition {
    private final State currentState;
    private final char alphabet;
    private final State nextState;

    public Transition(State currentState, char alphabet, State nextState) {
        this.currentState = currentState;
        this.alphabet = alphabet;
        this.nextState = nextState;
    }


    public State nextState(State currentState, char string) {
        return isDefinedAt(currentState, string) ? nextState : null;
    }

    public boolean isDefinedAt(State currentState, char alphabet) {
        return currentState.equals(this.currentState) && this.alphabet == (alphabet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transition that = (Transition) o;

        if (alphabet != that.alphabet) return false;
        if (currentState != null ? !currentState.equals(that.currentState) : that.currentState != null) return false;
        return nextState != null ? nextState.equals(that.nextState) : that.nextState == null;

    }

    @Override
    public int hashCode() {
        int result = currentState != null ? currentState.hashCode() : 0;
        result = 31 * result + (int) alphabet;
        result = 31 * result + (nextState != null ? nextState.hashCode() : 0);
        return result;
    }
}
