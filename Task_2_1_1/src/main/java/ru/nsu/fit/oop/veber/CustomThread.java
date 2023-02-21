package ru.nsu.fit.oop.veber;

public class CustomThread extends Thread {
    private final Integer[] arr;
    private final PrimeNumberFinder finder;
    private Boolean flag;

    public CustomThread(Integer[] arr, Boolean flag, PrimeNumberFinder finder) {
        this.arr = arr;
        this.flag = flag;
        this.finder = finder;
    }

    @Override
    public void run() {
        for (Integer x : arr) {
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
