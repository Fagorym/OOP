package ru.nsu.fit.oop.veber;

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
        return false;
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
