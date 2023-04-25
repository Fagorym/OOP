package ru.nsu.fit.oop.veber;

public class PrimeNumberFinderImpl implements PrimeNumberFinder {
    private final Integer[] arr;

    public PrimeNumberFinderImpl(Integer[] arr) {
        this.arr = arr;
    }

    @Override
    public Boolean haveNotPrime() {
        checkArrayCorrectness(arr);
        for (Integer integer : arr) {
            if (isNotPrime(integer)) {
                return true;
            }
        }
        return false;
    }
}
