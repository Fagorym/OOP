package ru.nsu.fit.oop.StringFinderTest;


import org.junit.jupiter.api.Assertions;
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
        Assertions.assertArrayEquals(new Object[]{30, 56, 730, 1043, 1049, 1054, 1059, 1064, 1069, 1074, 1079, 1084}, res.toArray());
    }

    @Test
    public void bigTestKmpFinder() throws IOException{
        InputStream inputStream = new FileInputStream("./biginput.txt");
        kmpFinder.setInputStream(inputStream);
        var index = kmpFinder.findSubstring("AB");

    }
}
