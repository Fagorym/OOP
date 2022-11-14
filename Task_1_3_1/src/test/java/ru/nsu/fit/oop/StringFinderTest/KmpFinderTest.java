package ru.nsu.fit.oop.StringFinderTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.KmpFinder;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for test our StringFinder class.
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
        System.out.println(res);
    }
}
