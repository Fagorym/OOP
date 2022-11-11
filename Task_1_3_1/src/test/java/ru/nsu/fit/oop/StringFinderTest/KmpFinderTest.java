package ru.nsu.fit.oop.StringFinderTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.KmpFinder;
import ru.nsu.fit.oop.Reader.StringFinder;

import java.io.IOException;

/**
 * Class for test our StringFinder class.
 */
public class KmpFinderTest {

    private KmpFinder kmpFinder;

    @BeforeEach
    public void initStringFinder() {
        kmpFinder = new KmpFinder("./input.txt");
    }

    @Test
    public void findWord() throws IOException {
        long index = kmpFinder.findSubstring("DOG");
        System.out.println(index);
    }
}
