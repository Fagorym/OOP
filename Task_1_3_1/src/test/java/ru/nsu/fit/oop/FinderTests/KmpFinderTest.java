package ru.nsu.fit.oop.FinderTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.KmpFinder;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for test our Knutt-Morris-PruttFinder class.
 */
public class KmpFinderTest {

    private KmpFinder kmpFinder;

    @BeforeEach
    public void initStringFinder() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("./input.txt");
        kmpFinder = new KmpFinder(inputStream);
    }

    @Test
    public void findWord() throws IOException {
        ArrayList<Integer> res = kmpFinder.findSubstring("DOG");
    }

    @Test
    public void bigTestKmpFinder() throws IOException {
        InputStream inputStream = new FileInputStream("./biginput.txt");
        kmpFinder.setInputStream(inputStream);
        var index = kmpFinder.findSubstring("AB");

    }
}
