package ru.nsu.fit.oop.veber.model;

import java.util.Random;

public class Food extends GeometricalObject {

    public Food(int x, int y) {
        super(x, y, CollisionObject.FOOD, '@');
    }

    public void generate(Box box, CollisionChecker collisionChecker) {
        do {
            Random random = new Random();
            this.setX(random.nextInt(box.getLength() - 2) + 1);
            this.setY(random.nextInt(box.getHeight() - 2) + 1);
        } while (!collisionChecker.addObject(this));
    }
}