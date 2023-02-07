package ru.nsu.fit.oop.veber.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.PrimeNumberFinder;

public class PrimeNumberFinderTest {
    private PrimeNumberFinder primeNumberFinder;

    @BeforeEach
    public void initFinder() {
        Integer[] arr = {4, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        primeNumberFinder = new PrimeNumberFinder(arr);
    }

    @Test
    public void testConsistentFinder() {
        Boolean answer = primeNumberFinder.consistentFinder();
        System.out.println(answer);
    }

    @Test
    public void testThreadFinder() {
        Boolean answer = primeNumberFinder.parallelFinder();
        System.out.println(answer);
    }

    @Test
    public void testParallelFinder() {
        Boolean answer = primeNumberFinder.parallelStreamFinder();
        System.out.println(answer);
    }
}
