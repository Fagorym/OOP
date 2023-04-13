package ru.nsu.fit.oop.veber.model;

public class GeometricalObject {
    private final Coordinate coordinate;
    private final CollisionObject collisionObject;

    private final char visualRepresentation;

    public GeometricalObject(int x, int y, CollisionObject collisionObject, Character visualRepresentation) {
        this.coordinate = new Coordinate(x, y);
        this.collisionObject = collisionObject;
        this.visualRepresentation = visualRepresentation;
    }

    public int getY() {
        return coordinate.getY();
    }

    public void setY(int y) {
        this.coordinate.setY(y);
    }

    public int getX() {
        return coordinate.getX();
    }

    public void setX(int x) {
        this.coordinate.setX(x);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public CollisionObject getCollisionObject() {
        return collisionObject;
    }

    public char getVisualRepresentation() {
        return visualRepresentation;
    }
}
