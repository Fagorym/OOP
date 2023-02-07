package ru.nsu.fit.oop.veber;

public class Util {
    public boolean isNotPrime(Integer x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }
}
