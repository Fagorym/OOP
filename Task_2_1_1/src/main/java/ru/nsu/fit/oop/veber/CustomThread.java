package ru.nsu.fit.oop.veber;

import java.util.Deque;

public class CustomThread extends Thread {
    private final Deque<Integer> deque;
    private Boolean flag;

    private final PrimeNumberFinder finder;

    public CustomThread(Deque<Integer> deque, Boolean flag, PrimeNumberFinder finder) {
        this.deque = deque;
        this.flag = flag;
        this.finder = finder;
    }

    @Override
    public void run() {
        while (!deque.isEmpty()) {
            Integer x = deque.pollFirst();

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
