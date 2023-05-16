package ru.nsu.fit.oop.veber.dto;

public class BaseDto implements Cloneable {
    private int x;
    private int y;

    public BaseDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public BaseDto clone() {
        try {
            BaseDto clone = (BaseDto) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
