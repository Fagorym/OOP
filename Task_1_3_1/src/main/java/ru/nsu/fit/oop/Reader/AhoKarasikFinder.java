package ru.nsu.fit.oop.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AhoKarasikFinder implements Finder {

    HashMap<Integer, HashMap<Character, Integer>> states;
    InputStream inputStream;

    int stringBufferSize = 1024;

    public AhoKarasikFinder(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public ArrayList<Integer> findSubstring(String inputSubString) throws IOException {
        int substringLen = inputSubString.length();
        while (stringBufferSize < substringLen) {
            stringBufferSize *= 2;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), stringBufferSize);
        generateAutomata(inputSubString);
        int readCount = -1;
        ArrayList<Integer> currentStates = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        currentStates.add(0);
        while (bufferedReader.ready()) {
            ArrayList<Integer> newStates = new ArrayList<>();
            readCount++;
            Character symbol = (char) bufferedReader.read();
            for (Integer state : currentStates) {

                if (states.get(state).containsKey(symbol)) {
                    if (states.get(state).get(symbol) == inputSubString.length()) {
                        res.add(readCount - inputSubString.length() + 1);
                        newStates = new ArrayList<>();
                        break;
                    }
                    newStates.add(states.get(state).get(symbol));

                }
                if (state != 0 && states.get(0).containsKey(symbol)) {
                    newStates.add(states.get(0).get(symbol));
                }

            }
            newStates.add(0);
            currentStates = newStates;


        }
        return res;

    }

    private void generateAutomata(String inputString) {
        char[] substring = inputString.toCharArray();
        Character[] characters = inputString.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        // HashSet<Character> characterHashSet = new HashSet<>(Arrays.asList(characters));
        HashMap<Integer, HashMap<Character, Integer>> states = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            HashMap<Character, Integer> currentStateMap = new HashMap<>();
            currentStateMap.put(characters[i], i + 1);
            states.put(i, currentStateMap);
        }

        this.states = states;
    }
}
