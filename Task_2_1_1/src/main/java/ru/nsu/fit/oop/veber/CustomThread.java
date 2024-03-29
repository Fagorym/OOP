package ru.nsu.fit.oop.veber;

public class CustomThread extends Thread {
    private final Integer[] arr;
    private final int start;
    private final int end;
    private final PrimeNumberFinder finder;
    private volatile boolean flag;

    public CustomThread(Integer[] arr, int startIndex, int endIndex, boolean flag, PrimeNumberFinder finder) {
        this.arr = arr;
        this.flag = flag;
        this.finder = finder;
        this.start = startIndex;
        this.end = endIndex;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (finder.isNotPrime(arr[i]) || this.isInterrupted()) {
                flag = true;
                return;
            }
        }

    }

    public Boolean getValue() {
        return flag;
    }
}
