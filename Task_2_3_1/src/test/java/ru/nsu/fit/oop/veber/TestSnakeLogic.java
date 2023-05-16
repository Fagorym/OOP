package ru.nsu.fit.oop.veber;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class TestSnakeLogic {

    private final int FOOD_COUNT = 3;

    private Environment environment;

    @BeforeEach
    public void init() {
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfiguration.setBoxHeight(1000);
        gameConfiguration.setBoxLength(1000);
        gameConfiguration.setFoodCount(FOOD_COUNT);
        GameConfiguration.setGameConfiguration(gameConfiguration);
        environment = new Environment();
    }

    @Test
    public void testSnakeMoving() {
        Snake snake = environment.getSnake();
        int oldX = snake.getHeadBlock().getX();
        int oldY = snake.getHeadBlock().getY();
        snake.move();
        Assertions.assertEquals(oldX + 1, snake.getHeadBlock().getX());
        Assertions.assertEquals(oldY, snake.getHeadBlock().getY());
        snake.setDirection(Direction.UP);
        snake.move();
        Assertions.assertEquals(oldX + 1, snake.getHeadBlock().getX());
        Assertions.assertEquals(oldY - 1, snake.getHeadBlock().getY());
        snake.setDirection(Direction.LEFT);
        snake.move();
        Assertions.assertEquals(oldX, snake.getHeadBlock().getX());
        Assertions.assertEquals(oldY - 1, snake.getHeadBlock().getY());
        snake.setDirection(Direction.DOWN);
        snake.move();
        Assertions.assertEquals(oldX, snake.getHeadBlock().getX());
        Assertions.assertEquals(oldY, snake.getHeadBlock().getY());
    }

    @Test
    public void testFoodGeneration() {
        Food food = Food.generate(environment.getBox(), environment.getCollisionChecker());
        int oldX = food.getX();
        int oldY = food.getY();
        food.getNewCoordinates(environment.getBox(), environment.getCollisionChecker());
        Assertions.assertNotEquals(oldX, food.getX());
        Assertions.assertNotEquals(oldY, food.getY());
        Assertions.assertEquals(environment.getFood().size(), FOOD_COUNT);

    }

    @Test
    public void checkCollisionChecker() {
        CollisionChecker collisionChecker = environment.getCollisionChecker();
        Food generatedFood = environment.getFood().get(0);
        Assertions.assertEquals(collisionChecker.checkCollision(generatedFood), generatedFood);
        collisionChecker.removeObject(generatedFood);
        Assertions.assertNotEquals(collisionChecker.checkCollision(generatedFood), generatedFood);
        Assertions.assertEquals(collisionChecker.checkCollision(generatedFood).getObjectType(), ObjectType.NOTHING);
    }

    @Test
    public void testSnakeGeneratingTail() {
        Snake snake = environment.getSnake();
        final int NEW_BLOCKS = 10;
        for (int i = 0; i < NEW_BLOCKS; i++) {
            snake.generateNewSnakeBlock();
            snake.move();
        }
        Assertions.assertEquals(snake.getBody().size(), NEW_BLOCKS);
        Assertions.assertEquals(snake.getHeadBlock().getY(), snake.getTailBlock().getY());
        Assertions.assertEquals(snake.getHeadBlock().getX() - 11, snake.getTailBlock().getX());
    }

}
