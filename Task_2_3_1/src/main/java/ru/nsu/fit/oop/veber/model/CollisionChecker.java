package ru.nsu.fit.oop.veber.model;

import java.util.Collection;

public class CollisionChecker {
    private final BoxElement[][] objects;

    public CollisionChecker(int height, int length) {
        objects = new BoxElement[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                objects[i][j] = new Nothing(i, j);
            }
        }
    }

    public BoxElement checkCollision(BoxElement obj) {
        return objects[obj.getX()][obj.getY()];
    }

    public boolean addObject(BoxElement obj) {
        if (objects[obj.getX()][obj.getY()].getObjectType() != ObjectType.NOTHING) {
            return false;
        }
        objects[obj.getX()][obj.getY()] = obj;
        return true;
    }

    public void addObjects(Collection<? extends BoxElement> objects) {
        for (BoxElement obj : objects) {
            addObject(obj);
        }
    }

    public void removeObject(BoxElement object) {
        objects[object.getX()][object.getY()] = new Nothing(object.getX(), object.getY());
    }
}
