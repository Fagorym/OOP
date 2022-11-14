package ru.nsu.fit.oop.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class, that implements Finder interface with Aho-Corasick algorithm.
 */
public class AhoCorasickFinder implements Finder {

    private HashMap<Integer, HashMap<Character, Integer>> states;
    private InputStream inputStream;

    private int stringBufferSize = 1024;

    /**
     * Default constructor for finder.
     *
     * @param inputStream - will be default stream to search in
     */
    public AhoCorasickFinder(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Method that sets default input stream.
     *
     * @param inputStream - will be next input stream
     */
    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @param inputSubString - what substring we need to find
     * @return ArrayList with all the indexes of substring in text
     * @throws IOException - When I/O error occurred
     */
    @Override
    public ArrayList<Integer> findSubstring(String inputSubString) throws IOException {
        int substringLen = inputSubString.length();
        while (stringBufferSize < substringLen) {
            stringBufferSize *= 2;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), stringBufferSize);
        generateStates(inputSubString);
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

    /**
     * Function that generates our automata with states.
     *
     * @param inputString - for what string we need to generate automata/tree
     */
    private void generateStates(String inputString) {
        Character[] characters = inputString.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        HashMap<Integer, HashMap<Character, Integer>> states = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            HashMap<Character, Integer> currentStateMap = new HashMap<>();
            currentStateMap.put(characters[i], i + 1);
            states.put(i, currentStateMap);
        }

        this.states = states;
    }
}
