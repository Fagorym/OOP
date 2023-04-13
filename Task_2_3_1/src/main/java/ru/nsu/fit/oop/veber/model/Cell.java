package ru.nsu.fit.oop.veber.model;

public class Cell extends GeometricalObject {

    public Cell(int x, int y) {
        super(x, y, CollisionObject.WALL, '#');
    }
}
