package com.step.parser;

import java.util.Set;

public class FATestCases {
    private final Set<String> passCases;
    private final Set<String> failCases;

    public FATestCases(Set<String> passCases, Set<String> failCases) {

        this.passCases = passCases;
        this.failCases = failCases;
    }

    public Set<String> getPassCases() {
        return passCases;
    }

    public Set<String> getFailCases() {
        return failCases;
    }
}
