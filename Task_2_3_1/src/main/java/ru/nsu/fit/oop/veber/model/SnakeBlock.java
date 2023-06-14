package ru.nsu.fit.oop.veber.model;

public class SnakeBlock extends BoxElement {
    private Snake owner;

    public SnakeBlock(int x, int y, Snake owner) {
        super(x, y, ObjectType.SNAKE);
        this.owner = owner;
    }

    public Snake getOwner() {
        return owner;
    }
}
