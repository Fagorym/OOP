package ru.nsu.fit.oop.veber.model;

public class KillerSnake extends ComputedSnake {
    public KillerSnake(int x, int y) {
        super(x, y);
    }

    @Override
    protected int convertToWeight(BoxElement element) {
        return switch (element.getObjectType()) {
            case FOOD, NOTHING -> 0;
            case SNAKE -> {
                SnakeBlock seenSnake = (SnakeBlock) element;
                if (!seenSnake.getOwner().equals(this)) {
                    yield 100;
                } else {
                    yield -100;
                }
            }
            case WALL -> -20;
        };
    }
}
