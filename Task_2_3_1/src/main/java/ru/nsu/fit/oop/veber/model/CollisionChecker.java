package ru.nsu.fit.oop.veber.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollisionChecker {
    private final Map<Coordinate, ObjectType> objectByCoordinate;

    public CollisionChecker() {
        objectByCoordinate = new HashMap<>();
    }

    public ObjectType checkCollision(BoxElement obj) {
        if (objectByCoordinate.containsKey(obj.getCoordinate())) {
            return objectByCoordinate.get(obj.getCoordinate());
        }
        return ObjectType.NOTHING;
    }

    public boolean addObject(BoxElement obj) {
        Coordinate objectCoordinate = obj.getCoordinate();
        if (objectByCoordinate.containsKey(objectCoordinate) &&
                objectByCoordinate.get(objectCoordinate) != ObjectType.NOTHING) {
            return false;
        }
        objectByCoordinate.put(objectCoordinate, obj.getCollisionObject());
        return true;
    }

    public void addObjects(Collection<? extends BoxElement> objects) {
        for (BoxElement obj : objects) {
            addObject(obj);
        }
    }

    public void removeObject(BoxElement object) {
        objectByCoordinate.remove(object.getCoordinate());
    }
}
