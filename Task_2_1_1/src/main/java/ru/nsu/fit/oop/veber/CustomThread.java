package ru.nsu.fit.oop.veber;

import java.util.Deque;
import java.util.concurrent.Semaphore;

public class CustomThread extends Thread {
    private final Deque<Integer> deque;
    private final PrimeNumberFinder finder;
    private final Semaphore semaphore;
    private Boolean flag;

    public CustomThread(Deque<Integer> deque, Boolean flag, PrimeNumberFinder finder,
                        Semaphore semaphore) {
        this.deque = deque;
        this.flag = flag;
        this.finder = finder;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            this.interrupt();
        }
        while (!deque.isEmpty()) {
            Integer x = deque.pollFirst();
            semaphore.release();
            if (finder.isNotPrime(x)) {
                flag = Boolean.TRUE;
                this.interrupt();
            }
        }
    }

    public Boolean getValue() {
        return flag;
    }
}
