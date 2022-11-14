package ru.nsu.fit.oop.StringFinderTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.BasicFinder;

import java.io.*;

/**
 * Class for test our StringFinder class.
 */
public class StringFinderTest {

    private BasicFinder stringFinder;

    @BeforeEach
    public void initStringFinder() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("./input.txt");
        stringFinder = new BasicFinder(inputStream);
    }

    @Test
    public void findWord() throws IOException {
        var index = stringFinder.findSubstring("DOG");
        System.out.println(index);
    }
}
