package ru.nsu.fit.oop.StringFinderTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.StringFinder;

import java.io.IOException;

/**
 * Class for test our StringFinder class.
 */
public class StringFinderTest {

    private StringFinder stringFinder;

    @BeforeEach
    public void initStringFinder() {
        stringFinder = new StringFinder("./input.txt");
    }

    @Test
    public void findWord() throws IOException {
        var index = stringFinder.findSubstring("DOG");
        System.out.println(index);
    }
}
