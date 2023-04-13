package ru.nsu.fit.oop.veber.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollisionChecker {
    private final Map<Coordinate, Boolean> isFilled;

    public CollisionChecker() {
        isFilled = new HashMap<>();
    }

    public boolean checkCollision(GeometricalObject obj) {
        return isFilled.get(obj.getCoordinate()) == null || isFilled.get(obj.getCoordinate());
    }

    public void addObject(GeometricalObject obj) {
        isFilled.put(obj.getCoordinate(), false);
    }

    public void addObjects(Collection<? extends GeometricalObject> objects) {
        for (GeometricalObject obj : objects) {
            addObject(obj);
        }
    }
}
