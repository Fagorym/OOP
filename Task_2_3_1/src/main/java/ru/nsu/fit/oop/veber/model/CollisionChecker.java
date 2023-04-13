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

    public boolean addObject(GeometricalObject obj) {
        Coordinate objectCoordinate = obj.getCoordinate();
        if (objectByCoordinate.containsKey(objectCoordinate) &&
                objectByCoordinate.get(objectCoordinate) != CollisionObject.NOTHING) {
            return false;
        }
        objectByCoordinate.put(obj.getCoordinate(), obj.getCollisionObject());
        return true;
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
