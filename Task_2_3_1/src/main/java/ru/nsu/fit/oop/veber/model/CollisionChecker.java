package ru.nsu.fit.oop.veber.model;

import java.util.Arrays;
import java.util.Collection;

public class CollisionChecker {
    private final ObjectType[][] objects;

    public CollisionChecker() {
        objects = new ObjectType[100][100];
        Arrays.stream(objects).forEach(x -> Arrays.fill(x, ObjectType.NOTHING));
    }

    public ObjectType checkCollision(BoxElement obj) {
        return objects[obj.getX()][obj.getY()];
    }

    public boolean addObject(BoxElement obj) {
        if (objects[obj.getX()][obj.getY()] != ObjectType.NOTHING) {
            return false;
        }
        objects[obj.getX()][obj.getY()] = obj.getObjectType();
        return true;
    }

    public void addObjects(Collection<? extends BoxElement> objects) {
        for (BoxElement obj : objects) {
            addObject(obj);
        }
    }

    public void removeObject(BoxElement object) {
        objects[object.getX()][object.getY()] = ObjectType.NOTHING;
    }
}
