package ru.nsu.fit.oop.veber.utils;

public enum Direction {
    left, right, down, up;

    public Direction getOpposite() {
        return switch (this) {
            case up -> down;
            case down -> up;
            case left -> right;
            case right -> left;
        };
    }
}
