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
        while (stringBufferSize < inputSubString.length()) {
            stringBufferSize *= 2;
        }
        buffer = new char[stringBufferSize];
        int charsCount = 0;
        boolean equals = true;
        int wrongSymbol = 0;
        int readCount = -1;
        do {
            readCount++;
            charsCount = readBuffer(stringBufferSize);
            findPrefixFunc();
            for (int i = 0; i < stringBufferSize; i++) {
                int symbolIterator = i;
                for (int j = 0; j < inputSubString.length(); j++) {
                    if (inputSubString.charAt(j) != buffer[symbolIterator]) {
                        equals = false;
                        wrongSymbol = j;
                        break;
                    }
                    symbolIterator++;

                }
                if (equals) return 1024 * readCount + i;
                else {
                    i += prefix[wrongSymbol];
                }
            }


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
