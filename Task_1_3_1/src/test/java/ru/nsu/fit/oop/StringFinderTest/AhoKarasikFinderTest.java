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
        System.out.println(index);
        Assertions.assertArrayEquals(new Object[]{30, 56, 730, 1043, 1049, 1054, 1059, 1064, 1069, 1074, 1079, 1084}, index.toArray());
    }
}
