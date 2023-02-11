package ru.nsu.fit.oop.veber;

public interface PrimeNumberFinder {

    Boolean haveNotPrime();

    default Boolean isNotPrime(Integer x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }
}
