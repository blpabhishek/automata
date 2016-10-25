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


    public State nextState(char alphabet) {
        return isDefinedAt(alphabet) ? nextState : null;
    }

    private boolean isDefinedAt(char alphabet) {
        return this.alphabet == (alphabet);
    }

    public State currentState() {
        return currentState;
    }

    public char getAlphabet() {
        return alphabet;
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

    @Override
    public String toString() {
        return "Transition{" +
                "currentState=" + currentState +
                ", alphabet=" + alphabet +
                ", nextState=" + nextState +
                '}';
    }
}
