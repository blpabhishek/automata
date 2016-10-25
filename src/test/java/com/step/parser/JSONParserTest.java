package com.step.parser;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONParserTest {
    @Test
    public void JSONTest() throws IOException {
        List<String> textFile = Files.readAllLines(Paths.get("resource/test_cases.json"), Charset.forName("UTF-8"));
        JSONParser jsonParser = JSONParser.parse(textFile.get(0));
        List<FATestRunner> testRunners = jsonParser.getTestRunners();
        for (FATestRunner testRunner : testRunners) {
            Assert.assertTrue(testRunner.validatePassCases());
            Assert.assertTrue(testRunner.validateFailCases());
        }
    }
}