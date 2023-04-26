package ru.nsu.fit.oop.veber.model;

public class Cell extends BoxElement {

    public Cell(int x, int y) {
        super(x, y, ObjectType.WALL, '#');
    }
}
