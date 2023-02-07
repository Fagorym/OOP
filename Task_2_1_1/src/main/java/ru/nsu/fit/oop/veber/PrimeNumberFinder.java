package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class PrimeNumberFinder {
    private final Util util = new Util();
    private final Integer[] arr;

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
        int threadCount = Thread.activeCount();
        Boolean interruptAll = false;
        boolean isAlive = true;
        CustomThread[] threads = new CustomThread[threadCount];
        Deque<Integer> deque = new ArrayDeque<>(List.of(arr));
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomThread(deque, false);
            threads[i].start();
        }
        while (isAlive) {
            isAlive = false;
            for (int i = 0; i < threadCount; i++) {
                if (threads[i].getValue() || interruptAll) {
                    interruptAll = true;
                    threads[i].interrupt();
                } else if (threads[i].isAlive()) {
                    isAlive = true;
                }
            }
        }
        return interruptAll;
    }

    public Boolean parallelStreamFinder() {
        List<Integer> list = Arrays.stream(arr).parallel().filter(util::isNotPrime).toList();
        return !list.isEmpty();
    }
}
