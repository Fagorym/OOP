package ru.nsu.fit.oop.veber.model;

import java.util.Random;

public class Food extends BoxElement {

    public Food() {
        super(0, 0, ObjectType.FOOD);

    }

    static public Food generate(Box box, CollisionChecker collisionChecker) {
        Food food = new Food();
        do {
            Random random = new Random();
            food.setX(random.nextInt(box.getLength() - 2) + 1);
            food.setY(random.nextInt(box.getHeight() - 2) + 1);
        } while (!collisionChecker.addObject(food));
        return food;
    }

    public void getNewCoordinates(Box box, CollisionChecker collisionChecker) {
        do {
            Random random = new Random();
            this.setX(random.nextInt(box.getLength() - 2) + 1);
            this.setY(random.nextInt(box.getHeight() - 2) + 1);
        } while (!collisionChecker.addObject(this));
    }
}