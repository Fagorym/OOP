package ru.nsu.fit.oop.Reader;

import java.io.*;

/**
 * Class, that realizes substring exploring in the file.
 */
public class StringFinder implements FinderInterface {
    private String inputFile;


    /**
     * Default constructor of the string finder.
     *
     * @param inputFile - in this file we want to find provided substring
     */
    public StringFinder(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Method that realizes basic search of substring in file.
     *
     * @param inputSubString - substring to find in the file.
     * @return index of the first appearance of the substring.
     * If there is no substring - returns 0.
     * @throws IOException
     */
    @Override
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

    /**
     * Method that changes input file.
     *
     * @param inputFile - will be new input file
     */
    @Override
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
}
