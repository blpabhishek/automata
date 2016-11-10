package com.step.parser;

import com.step.automata.*;

import java.util.Set;

public class FATestRunner {
    private final String name;
    private final String type;
    private final Tuple tuple;
    private final Set<String> passCases;
    private final Set<String> failCases;

    public FATestRunner(String name, String type, Tuple tuple, Set<String> passCases, Set<String> failCases) {
        this.name = name;
        this.type = type;
        this.tuple = tuple;
        this.passCases = passCases;
        this.failCases = failCases;
    }

    public boolean validatePassCases() {
        Machine dfaMachine = getMachine();
        for (String passCase : passCases) {
            if (!dfaMachine.check(passCase)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFailCases() {
        Machine dfaMachine = getMachine();
        for (String failCase : failCases) {
            if (dfaMachine.check(failCase)) {
                return false;
            }
        }
        return true;
    }

    public Machine getMachine() {
        return tuple.getMachine(type);
    }

    public String getName() {
        return name;
    }
}
