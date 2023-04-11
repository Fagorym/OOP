package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Snake {

    Deque<Snake> tail;
    private int x;
    private int y;

    private Direction direction;

    private Snake headBlock;

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.tail = new ArrayDeque<>();
        this.tail.add(this);
        this.headBlock = this;
        this.direction = Direction.right;
    }

    public Snake(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.tail = new ArrayDeque<>();
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void checkFoodCollision(Food food) {
        if (food.getX() == headBlock.x && food.getY() == headBlock.y) {
            food.setX(new Random().nextInt(15) + 2);
            food.setY(new Random().nextInt(15) + 2);
            Snake newTail = switch (direction) {
                case down -> new Snake(x, y - 1, direction);
                case right -> new Snake(x - 1, y, direction);
                case left -> new Snake(x + 1, y, direction);
                case up -> new Snake(x, y + 1, direction);
            };
            tail.addLast(newTail);
        }
    }


    public void checkBoxCollision() {
        if (x == 0 || x == 40) {
            System.out.println("GAME OVER");
        } else if (y == 0 || y == 40) {
            System.out.println("GAME OVER");
        }
    }


    public void update() {
        Snake headBlock = tail.peekFirst();
        Snake tailBlock = tail.pollLast();
        tailBlock.y = headBlock.y;
        tailBlock.x = headBlock.x;
        {
            switch (direction) {
                case right -> tailBlock.x = headBlock.x + 1;
                case left -> tailBlock.x = headBlock.x - 1;
                case down -> tailBlock.y = headBlock.y + 1;
                case up -> tailBlock.y = headBlock.y - 1;
            }
        }
        this.headBlock = tailBlock;
        tail.addFirst(tailBlock);

    }

    public Deque<Snake> getTail() {
        return tail;
    }
}
