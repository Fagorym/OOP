package ru.nsu.fit.oop.veber.model;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    private int hashCode;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Coordinate point) {
            return point.x == x && point.y == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        hashCode = Objects.hash(x, y);
    }

    public int getY() {
        return y;

    }

    public void setY(int y) {
        this.y = y;
        hashCode = Objects.hash(x, y);
    }
}
