package ru.nsu.fit.oop.veber;

import java.util.Arrays;
import java.util.List;

public class PrimeNumberFinder {
    private final Integer[] arr;

    public PrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
    }

    public Boolean consistentFinder() {
        for (Integer integer : arr) {
            if (isNotPrime(integer)) {
                return true;
            }
        }
        return false;
    }

    public Boolean parallelFinder() {
        return true;
    }

    public Boolean parallelStreamFinder() {
        List<Integer> list = Arrays.stream(arr).parallel().filter(this::isNotPrime).toList();
        return !list.isEmpty();
    }

    private boolean isNotPrime(Integer x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }
}
