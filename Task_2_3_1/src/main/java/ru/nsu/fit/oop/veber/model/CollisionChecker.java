package ru.nsu.fit.oop.veber.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollisionChecker {
    private final Map<Coordinate, CollisionObject> objectByCoordinate;

    public CollisionChecker() {
        objectByCoordinate = new HashMap<>();
    }

    public CollisionObject checkCollision(GeometricalObject obj) {
        if (objectByCoordinate.containsKey(obj.getCoordinate())) {
            return objectByCoordinate.get(obj.getCoordinate());
        }
        return CollisionObject.NOTHING;
    }

    public void addObject(GeometricalObject obj) {
        objectByCoordinate.put(obj.getCoordinate(), obj.getCollisionObject());
    }

    public void addObjects(Collection<? extends GeometricalObject> objects) {
        for (GeometricalObject obj : objects) {
            addObject(obj);
        }
    }

    public void removeObject(GeometricalObject object) {
        objectByCoordinate.put(object.getCoordinate(), CollisionObject.NOTHING);
    }
}
