package ru.nsu.fit.oop.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Interface for finder.
 */
public interface Finder {

    /**
     * Method that sets input stream.
     *
     * @param inputStream - will be next input reader
     */
    void setInputStream(InputStream inputStream);

    /**
     * Main method, that implements different algorithms of string exploring in some texts.
     *
     * @param inputSubString - what substring we need to find
     * @return - indexes of all substring positions
     * @throws IOException - if I/O error occurs
     */
    ArrayList<Integer> findSubstring(String inputSubString) throws IOException;
}
