package ru.nsu.fit.oop.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements Finder interface with Knut-Morris-Prutt algorithm.
 */
public class KmpFinder implements Finder {
    private int stringBufferSize = 1024;
    private String inputFile;
    private int[] prefix;


    /**
     * Basic constructor of Kmp Finder.
     *
     * @param inputFile - in which file we need to find substring
     */
    public KmpFinder(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * This method need to set input file.
     *
     * @param inputFile - will be next input file
     */
    @Override
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Method, that realizes algorithm of Knut-Morris-Prutt, that needs to find indexes of substring.
     *
     * @param inputSubString - what substring we need to find
     * @return indexes of all substring positions
     * @throws IOException - when I/O error occurs
     */
    @Override
    public ArrayList<Integer> findSubstring(String inputSubString) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile), stringBufferSize);
        findPrefixFunction(inputSubString);
        char[] substring = inputSubString.toCharArray();
        int substringLen = inputSubString.length();
        int readCount = -1;
        while (stringBufferSize < substringLen) {
            stringBufferSize *= 2;
        }
        ArrayList<Integer> found = new ArrayList<>();
        int charsCount = 0;
        int symbolIterator = 0;
        do {
            readCount++;
            char[] buffer = new char[stringBufferSize];
            charsCount = bufferedReader.read(buffer, 0, stringBufferSize);
            for (int i = 0; i < charsCount; i++) {
                while (symbolIterator > 0 && buffer[i] != substring[symbolIterator]) {
                    symbolIterator = prefix[symbolIterator - 1];
                }
                if (buffer[i] == substring[symbolIterator]) {
                    symbolIterator += 1;
                }
                if (symbolIterator == substringLen) {
                    found.add(readCount * stringBufferSize + i - substringLen + 1);
                    symbolIterator = 0;
                }
            }
        } while (charsCount == stringBufferSize);

        return found;
    }


    // OLEG WORK! WORKING HARD!!
    private void findPrefixFunction(String inputString) {
        char[] substring = inputString.toCharArray();
        int len = inputString.length();
        prefix = new int[len];
        prefix[0] = 0;
        for (int i = 1; i < len; i++) {
            int j = prefix[i - 1];
            while (j > 0 && substring[j] != substring[i]) {
                j = prefix[j - 1];
            }
            if (substring[i] == substring[j]) {
                ++j;
            }
            prefix[i] = j;

        }

    }
}
