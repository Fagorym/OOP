package ru.nsu.fit.oop.Reader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for finder.
 */
public interface Finder {

    /**
     * Method that must set input file.
     *
     * @param inputFile - will be next input file
     */
    void setInputFile(String inputFile);

    /**
     * Main method, that implements different algorithms of string exploring in some texts.
     *
     * @param inputSubString - what substring we need to find
     * @return - indexes of all substring positions
     * @throws IOException - if I/O error occurs
     */
    ArrayList<Integer> findSubstring(String inputSubString) throws IOException;
}
