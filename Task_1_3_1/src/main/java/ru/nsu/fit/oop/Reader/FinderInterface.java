package ru.nsu.fit.oop.Reader;

import java.io.IOException;

public interface FinderInterface {

    void setInputFile(String inputFile);

    long findSubstring(String inputSubString) throws IOException;
}
