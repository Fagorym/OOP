package ru.nsu.fit.oop.veber.utils;

public enum Direction {
    LEFT, RIGHT, DOWN, UP;

    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
