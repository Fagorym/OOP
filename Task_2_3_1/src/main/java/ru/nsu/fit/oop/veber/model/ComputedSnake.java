package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.Arrays;
import java.util.Random;

public class ComputedSnake extends Snake {
    private final int[][] memory = new int[100][100];


    public ComputedSnake(int x, int y) {
        super(x, y);
        for (int[] booleans : memory) {
            Arrays.fill(booleans, 0);
        }
    }

    public boolean isNotDead() {
        return isNotDead;
    }

    @Override
    public void move() {
        super.move();
        memory[getHeadBlock().getX()][getHeadBlock().getY()]++;
    }

    public void computeDirection(BoxElement[][] elements) {
        recursion(getHeadBlock().getX(), getHeadBlock().getY(), elements, 20);
        if (TempSnake.x == getHeadBlock().getX()) {
            if (TempSnake.y == getHeadBlock().getY() - 1) {
                if (this.direction != Direction.DOWN) {
                    setDirection(Direction.UP);
                } else {
                    setDirection(Direction.RIGHT);
                }
            } else {
                if (this.direction != Direction.UP) {
                    setDirection(Direction.DOWN);
                } else {
                    setDirection(Direction.LEFT);
                }
            }
        } else {
            if (TempSnake.x == getHeadBlock().getX() - 1) {
                if (this.direction != Direction.RIGHT) {
                    setDirection(Direction.LEFT);
                } else {
                    setDirection(Direction.DOWN);
                }
            } else {
                if (this.direction != Direction.LEFT) {
                    setDirection(Direction.RIGHT);
                } else {
                    setDirection(Direction.UP);
                }
            }

        }

    }

    protected int convertToWeight(BoxElement element) {
        return switch (element.getObjectType()) {
            case FOOD -> 330;
            case SNAKE -> {
                SnakeBlock seenBlock = (SnakeBlock) element;
                Snake snake = seenBlock.getOwner();
                if (snake.getHeadBlock() == seenBlock) {
                    yield -500;
                } else if (snake.getTailBlock().equals(seenBlock)) {
                    yield -500;
                } else {
                    yield -500;
                }
            }
            case WALL -> -350;
            case NOTHING -> 0;
        };

    }

    private void recursion(int x, int y, BoxElement[][] field, int maxStep) {
        int maxValue = Integer.MIN_VALUE;
        int leftValue;
        int rightValue;
        for (int j = -1; j <= 1; j += 2) {
            leftValue = rightValue = 0;
            for (int i = 1; i < maxStep; i++) {
                leftValue += (convertToWeight(field[x + j * i][y]) + memory[x + j * i][y] * -4) * Math.pow((maxStep - i), 2);
                if (field[x + j * i][y].getObjectType() == ObjectType.WALL) {
                    if (i == 1) {
                        leftValue = Integer.MIN_VALUE;
                    }
                    break;
                }
            }
            for (int i = 1; i < maxStep; i++) {
                rightValue += (convertToWeight(field[x][y + j * i]) + memory[x][y + j * i] * -4) * Math.pow((maxStep - i), 2);
                if (field[x][y + j * i].getObjectType() == ObjectType.WALL) {
                    if (i == 1) {
                        rightValue = Integer.MIN_VALUE;
                    }
                    break;
                }
            }
            if (leftValue > maxValue) {
                maxValue = leftValue;
                TempSnake.x = x + j;
                TempSnake.y = y;
            }
            if (rightValue > maxValue || (rightValue == maxValue && new Random().nextBoolean())) {
                maxValue = rightValue;
                TempSnake.x = x;
                TempSnake.y = y + j;
            }
        }
    }

    public void checkCollision(CollisionChecker collisionChecker, Box box) {
        if (!isNotDead) {
            return;
        }
        BoxElement collisionElement = collisionChecker.checkCollision(this.getHeadBlock());
        switch (collisionElement.getObjectType()) {
            case SNAKE -> {
                SnakeBlock deadBlock = (SnakeBlock) collisionElement;
                Snake snake = deadBlock.getOwner();
                if (snake.equals(this)) {
                    isNotDead = false;
                }
                if (snake.getHeadBlock().equals(deadBlock)) {
                    isNotDead = false;
                    snake.isNotDead = false;
                }
                int killedCountBlocks = snake.killBlocksAfter(deadBlock);
                for (int i = 0; i < killedCountBlocks; i++) {
                    collisionChecker.addObject(generateNewSnakeBlock());
                }
            }
            case WALL -> isNotDead = false;
            case FOOD -> {
                Food food = (Food) collisionElement;
                collisionChecker.removeObject(food);
                food.getNewCoordinates(box, collisionChecker);
                collisionChecker.addObject(this.generateNewSnakeBlock());
            }
            case NOTHING -> {

            }
        }


    }

    /*
    private int recursion(int x, int y, BoxElement[][] field, int startValue, int step, int maxStep) {
        int memoryCef = memory[x][y] ? -3 : 3;
        if (isVisited[x][y]) {
            return startValue + convertToWeight(field[x][y]) + memoryCef;
        }
        isVisited[x][y] = true;
        if (field[x][y].getObjectType() == ObjectType.WALL || step == maxStep) {
            return startValue + convertToWeight(field[x][y]) + memoryCef;
        } else {
            int maxValue = Integer.MIN_VALUE;
            for (int j = -1; j <= 1; j += 2) {
                int leftValue = recursion(x, y + j, field, startValue + convertToWeight(field[x][y]), step + 1, maxStep);
                int rightValue = recursion(x + j, y, field, startValue + convertToWeight(field[x][y]), step + 1, maxStep);
                if (leftValue > maxValue) {
                    maxValue = leftValue;
                    if (step == 0) {
                        TempSnake.y = y + j;
                        TempSnake.x = x;
                        TempSnake.value = leftValue;
                    }

                }
                if (rightValue > maxValue) {
                    maxValue = rightValue;
                    if (step == 0) {
                        TempSnake.y = y;
                        TempSnake.x = x + j;
                        TempSnake.value = rightValue;
                    }
                }
            }
            return maxValue;
        }

    }

     */

    public static class TempSnake {
        static int x;
        static int y;
        static int value;
    }
}
