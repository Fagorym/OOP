package ru.nsu.fit.oop.Reader;

import java.io.*;
import java.util.ArrayList;

/**
 * Class, that implements Finder interface with basic-search algorithm.
 */
public class BasicFinder implements Finder {
    private InputStream inputStream;

    /**
     * Default constructor of the string finder.
     *
     * @param inputStream - in this stream we want to find provided substring
     */
    public BasicFinder(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Method that implements basic search of substring in file.
     *
     * @param inputSubString - substring to find in the file.
     * @return index of the first appearance of the substring.
     * If there is no substring - returns 0.
     * @throws IOException when I/O exception occurred
     */
    @Override
    public ArrayList<Integer> findSubstring(String inputSubString) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<Integer> res = new ArrayList<>();
        Integer index = -1;
        boolean equals = true;
        String buffer = "";
        while (bufferedReader.ready()) {
            for (int i = 0; i < inputSubString.length(); i++) {
                equals = true;
                index++;
                buffer += (char) bufferedReader.read();
                if (buffer.charAt(i) != inputSubString.charAt(i)) {
                    equals = false;
                    index -= i;
                    break;
                }
            }
            if (equals) {
                res.add(index - inputSubString.length() + 1);
                buffer = "";
            } else {
                buffer = buffer.substring(1);
            }
        }
        return res;
    }

    /**
     * Method that changes input stream.
     *
     * @param inputStream - will be new input stream
     */
    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
