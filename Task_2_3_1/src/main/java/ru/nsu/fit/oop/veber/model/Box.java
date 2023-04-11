package ru.nsu.fit.oop.veber.model;

public class Box {
    private final int length;
    private final int height;

    public Box(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}
