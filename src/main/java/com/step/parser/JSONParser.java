package com.step.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.*;

public class JSONParser {
    private final JsonArray jsonArray;

    private JSONParser(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<FATestRunner> getTestRunners() {
        List<FATestRunner> dfaList = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            dfaList.add(parseElement(jsonElement));
        }
        return dfaList;
    }

    private Set<String> mapToList(JsonElement jsonObject) {
        JsonArray jsonArray = jsonObject.getAsJsonArray();
        Set<String> set = new HashSet<>();
        for (JsonElement element : jsonArray) {
            set.add(element.getAsString());
        }
        return set;
    }

    private Tuple parseTuple(JsonObject tuple) {
        Set<String> states = mapToList(tuple.get("states"));
        Set<String> alphabets = mapToList(tuple.get("alphabets"));
        String startStates = tuple.get("start-state").getAsString();
        Set<String> finalStates = mapToList(tuple.get("final-states"));
        JsonObject delta = tuple.get("delta").getAsJsonObject();
        Map<String, Map<String, String>> transitions = parseDelta(delta, states);
        return new Tuple(states, alphabets, startStates, finalStates, transitions);
    }

    private Map<String, Map<String, String>> parseDelta(JsonObject delta, Set<String> states) {
        HashMap<String, Map<String, String>> transitions = new HashMap<>();
        for (String state : states) {
            String memberName = makeString(state);
            JsonElement jsonElement = delta.get(memberName);
            if (jsonElement != null) {
                addTransitions(transitions, memberName, jsonElement);
            }
        }
        return transitions;
    }

    private void addTransitions(HashMap<String, Map<String, String>> transitions, String memberName, JsonElement jsonElement) {
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            Map<String, String> subTransitions = addSubTransitions(transitions, memberName, entry);
            transitions.put(memberName, subTransitions);
        }
    }

    private Map<String, String> addSubTransitions(HashMap<String, Map<String, String>> transitions, String memberName, Map.Entry<String, JsonElement> entry) {
        Map<String, String> hashMap = transitions.containsKey(memberName) ? transitions.get(memberName) : new HashMap<String, String>();
        try {
            JsonArray asJsonArray = entry.getValue().getAsJsonArray();
            for (JsonElement element : asJsonArray) {
                hashMap.put(entry.getKey(), makeString(element.toString()));
            }
        } catch (Exception ignored) {
            hashMap.put(entry.getKey(), makeString(entry.getValue().toString()));
        }
        return hashMap;
    }

    private String makeString(String state) {
        return state.replaceAll("\"", "");
    }

    private FATestRunner parseElement(JsonElement element) {
        JsonObject jsonObject = element.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String type = jsonObject.get("type").getAsString();
        Set<String> passCases = mapToList(jsonObject.get("pass-cases"));
        Set<String> failCases = mapToList(jsonObject.get("fail-cases"));
        JsonObject jsonTuple = jsonObject.get("tuple").getAsJsonObject();
        Tuple tuple = parseTuple(jsonTuple);
        return new FATestRunner(name, type, tuple, passCases, failCases);
    }

    public static JSONParser parse(String jsonString) throws IOException {
        JsonParser parser = new JsonParser();
        String pureJson = jsonString.replaceAll("\\\\", "");
        pureJson = pureJson.substring(1, pureJson.length() - 1);
        JsonArray jsonArray = parser.parse(pureJson).getAsJsonArray();
        return new JSONParser(jsonArray);
    }
}
