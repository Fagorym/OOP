package ru.nsu.fit.oop.veber.model;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {

    List<Snake> tail;
    private int x;
    private int y;

    private Direction direction;

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.tail = new ArrayList<>();
        this.direction = Direction.right;
    }

    public Snake(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.tail = new ArrayList<>();
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

    public void checkFoodCollision(Food food) {
        if (food.getX() == x && food.getY() == y) {
            food.setX(new Random().nextInt(15));
            food.setY(new Random().nextInt(15));
            Snake newTail = switch (direction) {
                case down -> new Snake(x, y - 1, direction);
                case right -> new Snake(x - 1, y, direction);
                case left -> new Snake(x + 1, y, direction);
                case up -> new Snake(x, y + 1, direction);
            };
            tail.add(newTail);
        }
    }


    public void checkBoxCollision() {
        if (x == 0) {
            if (y == 0 || y == 40) {
                System.out.println("GAME OVER");
            }
        } else if (y == 0) {
            if (x == 0 || x == 40) {
                System.out.println("GAME OVER");
            }
        }
    }


    public void update() {
        switch (direction) {
            case right -> x += 1;
            case left -> x -= 1;
            case down -> y += 1;
            case up -> y -= 1;
        }
    }
}
