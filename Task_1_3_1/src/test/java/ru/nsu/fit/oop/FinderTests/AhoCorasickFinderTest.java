package ru.nsu.fit.oop.FinderTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.AhoCorasickFinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for test our AhoCorasickFinder class.
 */
public class AhoCorasickFinderTest {
    AhoCorasickFinder ahoCorasickFinder;


    @BeforeEach
    public void initFinder() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("./input.txt");
        ahoCorasickFinder = new AhoCorasickFinder(inputStream);
    }

    @Test
    public void testAhoCorasickFinder() throws IOException {
        var index = ahoCorasickFinder.findSubstring("DOG");
    }

    @Test
    public void bigTestAhoCorasickFinder() throws IOException{
        InputStream inputStream = new FileInputStream("./biginput.txt");
        ahoCorasickFinder.setInputStream(inputStream);
        var index = ahoCorasickFinder.findSubstring("AB");
        System.out.println(index);

    }
}
