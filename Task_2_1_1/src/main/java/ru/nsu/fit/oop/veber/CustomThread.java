package ru.nsu.fit.oop.veber;

public class CustomThread extends Thread {
    private final Integer[] arr;
    private final int start;
    private final int end;
    private final PrimeNumberFinder finder;
    private Boolean flag;

    public CustomThread(Integer[] arr, int startIndex, int endIndex, Boolean flag, PrimeNumberFinder finder) {
        this.arr = arr;
        this.flag = flag;
        this.finder = finder;
        this.start = startIndex;
        this.end = endIndex;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (finder.isNotPrime(arr[i])) {
                flag = Boolean.TRUE;
                this.interrupt();
            }
        }

    }

    public Boolean getValue() {
        return flag;
    }
}
