package ru.nsu.fit.oop.veber;

import java.util.Arrays;

public class ParallelStreamPrimeNumberFinder implements PrimeNumberFinder {
    private final Integer[] arr;

    public ParallelStreamPrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
    }


    @Override
    public Boolean haveNotPrime() {
        checkArrayCorrectness(arr);
        return Arrays.stream(arr).parallel().anyMatch(this::isNotPrime);
    }
}
