package ru.nsu.fit.oop.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements Finder interface with Knut-Morris-Prutt algorithm.
 */
public class KmpFinder implements Finder {
    private int stringBufferSize = 1024;
    private InputStream inputStream;
    private int[] prefix;


    /**
     * Basic constructor of Kmp Finder.
     *
     * @param inputStream - in which stream we need to find substring
     */
    public KmpFinder(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * This method need to set input stream.
     *
     * @param inputStream - will be next input file
     */
    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
        int substringLen = inputSubString.length();
        while (stringBufferSize < substringLen) {
            stringBufferSize *= 2;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), stringBufferSize);
        findPrefixFunction(inputSubString);
        char[] substring = inputSubString.toCharArray();
        int readCount = -1;
        ArrayList<Integer> found = new ArrayList<>();
        int substringIterator = 0;
        do {
            readCount++;
            char symbol = (char) bufferedReader.read();
                while (substringIterator > 0 && symbol != substring[substringIterator]) {
                    substringIterator = prefix[substringIterator - 1];
                }
                if (symbol == substring[substringIterator]) {
                    substringIterator += 1;
                }
                if (substringIterator == substringLen) {
                    found.add(readCount - substringLen + 1);
                    substringIterator = 0;
                }

        } while (bufferedReader.ready());

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
