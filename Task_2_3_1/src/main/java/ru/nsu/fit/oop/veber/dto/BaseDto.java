package ru.nsu.fit.oop.veber.dto;

public class BaseDto {
    private final int x;
    private final int y;

    public BaseDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
