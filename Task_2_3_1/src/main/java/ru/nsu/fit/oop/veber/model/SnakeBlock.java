package ru.nsu.fit.oop.veber.model;

public class SnakeBlock extends GeometricalObject {
    public SnakeBlock(int x, int y) {
        super(x, y, CollisionObject.SNAKE, '$');
    }
}
