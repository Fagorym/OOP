package ru.nsu.fit.oop.Reader;

import java.io.*;
import java.util.Arrays;

public class KmpFinder implements FinderInterface {
    int stringBufferSize = 1024;
    char[] buffer;
    private String inputFile;
    private int[] prefix;


    public KmpFinder(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public long findSubstring(String inputSubString) throws IOException {
        char[] substring = inputSubString.toCharArray();
        while (stringBufferSize < inputSubString.length()) {
            stringBufferSize *= 2;
        }
        buffer = new char[stringBufferSize];
        int charsCount = 0;
        int findedIndex = -1;
        int readCount = -1;
        int symbolIterator = 0;
        for (int i = 0; i < stringBufferSize; i++) {
            while(symbolIterator>0 && buffer[i] != substring[symbolIterator]){
                symbolIterator = prefix[symbolIterator-1];
            }
            if (buffer[i] == substring[symbolIterator]){
                symbolIterator+=1;
            }
            if (symbolIterator == stringBufferSize){
                findedIndex = i - inputSubString.length() +1;
                break;
            }
        }

        return findedIndex;
    }

    private int readBuffer(int len) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile), stringBufferSize);
        return bufferedReader.read(buffer, 0, len);
    }

    // OLEG WORK! WORKING HARD!!
    public void findPrefixFunc(String inputString) {
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
