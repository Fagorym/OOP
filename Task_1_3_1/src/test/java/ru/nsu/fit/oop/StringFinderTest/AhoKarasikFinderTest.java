package ru.nsu.fit.oop.StringFinderTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.Reader.AhoKarasikFinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AhoKarasikFinderTest {
    AhoKarasikFinder ahoKarasikFinder;


    @BeforeEach
    public void initFinder() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("./input.txt");
        ahoKarasikFinder = new AhoKarasikFinder(inputStream);
    }

    @Test
    public void testAhoKarasik() throws IOException {
        var index = ahoKarasikFinder.findSubstring("DOG");
    }

    @Test
    public void bigTestAhoKarasik() throws IOException{
        InputStream inputStream = new FileInputStream("./biginput.txt");
        ahoKarasikFinder.setInputStream(inputStream);
        var index = ahoKarasikFinder.findSubstring("AB");
        System.out.println(index);

    }
}
