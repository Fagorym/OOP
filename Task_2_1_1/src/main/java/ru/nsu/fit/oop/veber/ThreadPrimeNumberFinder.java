package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ThreadPrimeNumberFinder implements PrimeNumberFinder {

    private final Integer[] arr;
    private Integer threadCount;

    public ThreadPrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
        this.threadCount = Thread.activeCount();
    }

    public void setThreadCount(Integer threadCount) {
        if (threadCount <= Thread.activeCount()) {
            this.threadCount = threadCount;
        }
    }

    @Override
    public Boolean haveNotPrime() {
        CustomThread[] threads = new CustomThread[threadCount];
        Deque<Integer> deque = new ArrayDeque<>(List.of(arr));
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomThread(deque, false, this);
            threads[i].start();
        }

        Boolean interruptAll = false;
        boolean isAlive = true;
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
}
