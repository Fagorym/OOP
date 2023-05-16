package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        for (int i = 0; i < gameConfiguration.getExtraWalls(); i++) {
            box.addWalls(generateWall(4,
                    gameConfiguration.getBoxLength(),
                    gameConfiguration.getBoxHeight()));

        }

        this.snake = Snake.generate(box, collisionChecker);

        foodList = new ArrayList<>();
        for (int i = 0; i < gameConfiguration.getFoodCount(); i++) {
            foodList.add(Food.generate(box, collisionChecker));
        }

        collisionChecker.addObject(snake.getHeadBlock());
        collisionChecker.addObjects(foodList);
        collisionChecker.addObjects(box.getWalls());

    }

    private List<Wall> generateWall(int maxSize, int boxHeight, int boxLength) {
        Random random = new Random();
        List<Wall> walls = new ArrayList<>();
        boolean isSizeWillBeHeight = random.nextBoolean();
        int height = random.nextInt(boxHeight - maxSize);
        int length = random.nextInt(boxLength - maxSize);
        if (isSizeWillBeHeight) {
            int newHeight = height + random.nextInt(maxSize) + 2;
            for (int i = height; i < newHeight; i++) {
                walls.add(new Wall(i, length));
            }
        } else {
            int newLength = length + random.nextInt(maxSize) + 2;
            for (int i = length; i < newLength; i++) {
                walls.add(new Wall(height, i));
            }
        }
        return walls;

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
