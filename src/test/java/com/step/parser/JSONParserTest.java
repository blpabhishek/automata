package com.step.parser;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JSONParserTest {
    @Test
    public void ShouldBeAbleToParseAFullJSONObject() throws IOException {
        String jsonContent = "\"[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]\"";
        JSONParser jsonParser = JSONParser.parse(jsonContent);
        List<FATestRunner> testRunners = jsonParser.getTestRunners();
        for (FATestRunner testRunner : testRunners) {
            Assert.assertTrue(testRunner.validatePassCases());
            Assert.assertTrue(testRunner.validateFailCases());
        }
    }
}