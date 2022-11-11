package ru.nsu.fit.oop.Reader;

import java.io.*;
import java.util.Arrays;

public class KmpFinder implements FinderInterface {
    int stringBufferSize = 1024;
    char[] buffer;
    private String inputFile;
    private int[] prefix;

    @Override
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public long findSubstring(String inputSubString) throws IOException {

        int charsCount = 0;
        do {
            charsCount = readBuffer(stringBufferSize - inputSubString.length() - 1);
            buffer[stringBufferSize] = '\0';
            System.arraycopy(inputSubString.toCharArray(), 0, buffer, stringBufferSize, inputSubString.length());
            findPrefixFunc();



        } while (charsCount == 1024);

        return 1;
    }

    private int readBuffer(int len) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile), stringBufferSize);
        return bufferedReader.read(buffer, 0, len);
    }

    // OLEG WORK! WORKING HARD!!
    public void findPrefixFunc() {
        prefix = new int[stringBufferSize];
        prefix[0] = 0;
        for (int i = 1; i < stringBufferSize; i++) {
            int j = prefix[i - 1];
            while (j > 0 && buffer[j] != buffer[i]) {
                j = prefix[j - 1];
            }
            if (buffer[i] == buffer[j]) {
                ++j;
            }
            prefix[i] = j;

        }

    }
}
