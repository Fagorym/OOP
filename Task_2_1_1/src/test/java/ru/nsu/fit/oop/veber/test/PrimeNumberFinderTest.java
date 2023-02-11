package ru.nsu.fit.oop.veber.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.ParallelStreamPrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinderImpl;
import ru.nsu.fit.oop.veber.ThreadPrimeNumberFinder;

public class PrimeNumberFinderTest {
    private final PrimeNumberFinder[] finders = new PrimeNumberFinder[3];

    @BeforeEach
    public void initFinder() {
        Integer[] arr = {4, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        finders[0] = new PrimeNumberFinderImpl(arr);
        finders[1] = new ThreadPrimeNumberFinder(arr);
        finders[2] = new ParallelStreamPrimeNumberFinder(arr);
    }

    @Test
    public void testConsistentFinder() {
        for (PrimeNumberFinder finder : finders) {
            Boolean answer = finder.haveNotPrime();
            System.out.println(answer);
        }
    }
}
