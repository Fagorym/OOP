package ru.nsu.fit.oop.veber.model;

public class KillerSnake extends ComputedSnake {
    public KillerSnake(int x, int y) {
        super(x, y);
    }

    @Override
    protected int convertToWeight(BoxElement element) {
        return switch (element.getObjectType()) {
            case FOOD -> 0;
            case SNAKE -> 100;
            case WALL -> -20;
            case NOTHING -> 0;
        };
    }
}
