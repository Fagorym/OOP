package ru.nsu.fit.oop.Reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AhoKarasikFinder implements Finder {

    InputStream inputStream;

    public AhoKarasikFinder(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public ArrayList<Integer> findSubstring(String inputSubString) throws IOException {
        return null;
    }

    private void generateAutomata
}
