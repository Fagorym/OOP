package ru.nsu.fit.oop.veber.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.oop.veber.ParallelStreamPrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinderImpl;
import ru.nsu.fit.oop.veber.ThreadPrimeNumberFinder;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PrimeNumberFinderTest {
    private final PrimeNumberFinder[] finders = new PrimeNumberFinder[3];

    private static Stream<Arguments> arrays() {
        return Stream.of(
                arguments(
                        new Integer[]{4, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053},
                        true));
    }


    @ParameterizedTest
    @MethodSource("arrays")
    public void testFinders(Integer[] arr, Boolean result) throws InterruptedException {
        finders[0] = new PrimeNumberFinderImpl(arr);
        finders[1] = new ThreadPrimeNumberFinder(arr);
        finders[2] = new ParallelStreamPrimeNumberFinder(arr);
        for (PrimeNumberFinder finder : finders) {
            Boolean answer = finder.haveNotPrime();
            Assertions.assertEquals(answer, result);
        }
    }

    @Test
    public void testNullArray() {
        finders[0] = new PrimeNumberFinderImpl(null);
        finders[1] = new ThreadPrimeNumberFinder(null);
        finders[2] = new ParallelStreamPrimeNumberFinder(null);
        for (PrimeNumberFinder finder : finders) {
            Assertions.assertThrows(IllegalArgumentException.class, finder::haveNotPrime);
        }
    }

    @Test
    public void testEmptyArrays() {
        Integer[] arr = {};
        finders[0] = new PrimeNumberFinderImpl(arr);
        finders[1] = new ThreadPrimeNumberFinder(arr);
        finders[2] = new ParallelStreamPrimeNumberFinder(arr);
        for (PrimeNumberFinder finder : finders) {
            Assertions.assertThrows(IllegalArgumentException.class, finder::haveNotPrime);
        }
    }
}
