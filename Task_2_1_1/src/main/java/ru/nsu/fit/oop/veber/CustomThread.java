package ru.nsu.fit.oop.veber;

import java.util.Deque;

public class CustomThread extends Thread {
    private final Deque<Integer> deque;
    private final Util util = new Util();
    private Boolean flag;

    public CustomThread(Deque<Integer> deque, Boolean flag) {
        this.deque = deque;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (!deque.isEmpty()) {
            Integer x = deque.pollFirst();

            if (util.isNotPrime(x)) {
                flag = Boolean.TRUE;
                this.interrupt();
            }
        }
    }

    public Boolean getValue() {
        return flag;
    }
}
