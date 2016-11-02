package com.step.automata;

public class State {
    private String state;

    public State(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state1 = (State) o;

        return state.equals(state1.state);

    }

    @Override
    public String toString() {
        return  state;
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }
}
