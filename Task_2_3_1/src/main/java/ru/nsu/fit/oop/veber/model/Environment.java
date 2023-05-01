package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class Environment {
    private final Snake snake;
    private final Food food;
    private final Box box;

    private final CollisionChecker collisionChecker;

    public Environment() {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        this.collisionChecker = new CollisionChecker(gameConfiguration.getConsoleScreenLength(),
                gameConfiguration.getConsoleScreenHeight());

        this.snake = new Snake(gameConfiguration.getSnakeInitialCoordinateX(),
                gameConfiguration.getSnakeInitialCoordinateY());

        this.food = new Food(gameConfiguration.getSnakeInitialCoordinateX(),
                gameConfiguration.getSnakeInitialCoordinateY());

        this.box = new Box(gameConfiguration.getConsoleScreenHeight(),
                gameConfiguration.getConsoleScreenHeight());

        collisionChecker.addObject(snake.getTailBlock());
        collisionChecker.addObject(food);
        collisionChecker.addObjects(box.getWalls());

    }

    public Box getBox() {
        return box;
    }

    public Food getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
}
