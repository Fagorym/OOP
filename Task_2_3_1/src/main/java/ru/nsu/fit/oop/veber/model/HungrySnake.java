package ru.nsu.fit.oop.veber.model;

public class HungrySnake extends ComputedSnake {
    public HungrySnake(int x, int y) {
        super(x, y);
    }

    @Override
    protected int convertToWeight(BoxElement element) {
        return switch (element.getObjectType()) {
            case FOOD -> 100;
            case SNAKE, NOTHING -> 0;
            case WALL -> -20;
        };
    }
}
