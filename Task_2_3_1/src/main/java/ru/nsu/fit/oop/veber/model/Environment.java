package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private final Snake snake;
    private final List<Food> foodList;
    private final Box box;

    private final CollisionChecker collisionChecker;

    public Environment() {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        this.collisionChecker = new CollisionChecker(gameConfiguration.getBoxHeight(),
                gameConfiguration.getBoxLength());


        this.box = new Box(gameConfiguration.getBoxLength(),
                gameConfiguration.getBoxHeight());

        this.snake = Snake.generate(box, collisionChecker);

        foodList = new ArrayList<>();
        for (int i = 0; i < gameConfiguration.getFoodCount(); i++) {
            foodList.add(Food.generate(box, collisionChecker));
        }

        collisionChecker.addObject(snake.getHeadBlock());
        collisionChecker.addObjects(foodList);
        collisionChecker.addObjects(box.getWalls());

    }

    public Box getBox() {
        return box;
    }

    public List<Food> getFood() {
        return foodList;
    }

    public Snake getSnake() {
        return snake;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
}
