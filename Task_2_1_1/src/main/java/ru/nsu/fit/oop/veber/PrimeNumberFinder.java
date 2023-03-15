package ru.nsu.fit.oop.veber;

public interface PrimeNumberFinder {

    /**
     * Function that checks, if in the list is not prime number.
     *
     * @return true - at least one number is not prime
     * false - all numbers is prime
     */
    Boolean haveNotPrime() throws InterruptedException;

    /**
     * Default function that checks, if the number is prime
     *
     * @param x input number
     * @return true - not prime, false - prime
     */
    default Boolean isNotPrime(Integer x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }

    default void checkArrayCorrectness(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
    }
}
