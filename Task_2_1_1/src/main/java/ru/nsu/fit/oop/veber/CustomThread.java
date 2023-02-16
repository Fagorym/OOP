package ru.nsu.fit.oop.veber;

import java.util.concurrent.SynchronousQueue;

public class CustomThread extends Thread {
    private final SynchronousQueue<Integer> queue;
    private final PrimeNumberFinder finder;
    private Boolean flag;

    public CustomThread(SynchronousQueue<Integer> queue, Boolean flag, PrimeNumberFinder finder) {
        this.queue = queue;
        this.flag = flag;
        this.finder = finder;
    }

    @Override
    public void run() {

        while (!queue.isEmpty()) {
            Integer x = queue.poll();
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
