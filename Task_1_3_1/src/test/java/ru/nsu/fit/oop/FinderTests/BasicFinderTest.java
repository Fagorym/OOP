package ru.nsu.fit.oop.FinderTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.BasicFinder;

import java.io.*;

/**
 * Class for test our StringFinder class.
 */
public class BasicFinderTest {

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
    @Test
    public void bigTestStringFinder() throws IOException{
        InputStream inputStream = new FileInputStream("./biginput.txt");
        stringFinder.setInputStream(inputStream);
        var index = stringFinder.findSubstring("AB");
        System.out.println(index);

    }
}
