package ru.nsu.fit.oop.veber;

import java.util.Deque;

public class CustomThread extends Thread {
    private final Deque<Integer> deque;
    private final PrimeNumberFinder finder;
    private Boolean flag;

    public CustomThread(Deque<Integer> deque, Boolean flag, PrimeNumberFinder finder) {
        this.deque = deque;
        this.flag = flag;
        this.finder = finder;
    }

    @Override
    public void run() {
        while (true) {

            Integer x;
            synchronized (deque) {
                if (deque.isEmpty()) {
                    break;
                }

                x = deque.pollFirst();
            }
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
