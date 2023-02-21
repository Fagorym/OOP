package ru.nsu.fit.oop.veber;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamPrimeNumberFinder implements PrimeNumberFinder {
    private final Integer[] arr;

    public ParallelStreamPrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
    }

    @Override
    public Boolean haveNotPrime() {
        checkArrayCorrectness(arr);
        List<Integer> list = Arrays.stream(arr).parallel().filter(this::isNotPrime).toList();
        return !list.isEmpty();
    }
}
