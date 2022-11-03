package ru.nsu.fit.oop.Reader;

import java.io.*;

public class StringFinder {
    private String inputSubString;
    private String inputFile;


    public StringFinder(String inputFile) {
        this.inputFile = inputFile;
    }

    public long findSubstring(String inputSubString) throws IOException {
        File file = new File(inputFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        long index = -1;
        boolean equals = true;
        String buffer = "";
        while (bufferedReader.ready()) {
            for (int i = 0; i < inputSubString.length(); i++) {
                equals = true;
                index++;
                buffer += (char) bufferedReader.read();
                if (buffer.charAt(i) != inputSubString.charAt(i)) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                return index - inputSubString.length() + 1;
            } else {
                buffer = buffer.substring(1);
            }
        }
        return -1;
    }

}
