package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Snake {

    Deque<SnakeBlock> tail;
    private Direction direction;

    private SnakeBlock headBlock;

    public Snake(int x, int y) {
        this.tail = new ArrayDeque<>();
        this.headBlock = new SnakeBlock(x, y);
        this.tail.add(headBlock);
        this.direction = Direction.right;
    }

    public int getX() {
        return headBlock.getX();
    }

    public int getY() {
        return headBlock.getY();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void checkFoodCollision(Food food, Box box) {
        if (food.getX() == headBlock.getX() && food.getY() == headBlock.getY()) {
            food.setX(new Random().nextInt(20) + 1);
            food.setY(new Random().nextInt(20) + 1);
            SnakeBlock newTail = switch (direction) {
                case down -> new SnakeBlock(headBlock.getX(), headBlock.getY() - 1);
                case right -> new SnakeBlock(headBlock.getX() + 1, headBlock.getY());
                case left -> new SnakeBlock(headBlock.getX() - 1, headBlock.getY());
                case up -> new SnakeBlock(headBlock.getX(), headBlock.getY() + 1);
            };
            tail.addLast(newTail);
        }
    }


    public void update() {
        SnakeBlock headBlock = tail.peekFirst();
        SnakeBlock tailBlock = tail.pollLast();
        tailBlock.setX(headBlock.getX());
        tailBlock.setY(headBlock.getY());
        {
            switch (direction) {
                case right -> tailBlock.setX(headBlock.getX() + 1);
                case left -> tailBlock.setX(headBlock.getX() - 1);
                case down -> tailBlock.setY(headBlock.getY() + 1);
                case up -> tailBlock.setY(headBlock.getY() - 1);
            }
        }
        this.headBlock = tailBlock;
        tail.addFirst(tailBlock);

    }

    public Deque<SnakeBlock> getTail() {
        return tail;
    }

    public SnakeBlock getHeadBlock() {
        return headBlock;
    }
}
