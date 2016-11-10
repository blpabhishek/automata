package com.step.examples;

import com.step.parser.FATestRunner;
import com.step.parser.JSONParser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DFAMain {
    public static void main(String[] args) throws IOException {
        run("resource/dfa_cases.json");
        run("resource/nfa_cases.json");
    }

    private static void run(String fileName) throws IOException {
        List<String> textFile = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));
        JSONParser jsonParser = JSONParser.parse(textFile.get(0));
        List<FATestRunner> testRunners = jsonParser.getTestRunners();
        for (FATestRunner testRunner : testRunners) {
            testRunner.validatePassCases();
            testRunner.validateFailCases();
        }
    }
}
