package ru.nsu.fit.oop.veber;

import java.util.Arrays;
import java.util.List;

public class PrimeNumberFinder {
    private final Integer[] arr;
    private final Util util = new Util();

    public PrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
    }

    public Boolean consistentFinder() {
        for (Integer integer : arr) {
            if (util.isNotPrime(integer)) {
                return true;
            }
        }
        return false;
    }

    public Boolean parallelFinder() {
        Boolean hasPrime = false;
        int threadCount = Thread.activeCount();
        CustomThread[] threads = new CustomThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomThread(arr, hasPrime);
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            if (threads[i].isAlive() && !hasPrime) {
                i = 0;
            }
        }
        return hasPrime;
    }

    public Boolean parallelStreamFinder() {
        List<Integer> list = Arrays.stream(arr).parallel().filter(util::isNotPrime).toList();
        return !list.isEmpty();
    }
}
