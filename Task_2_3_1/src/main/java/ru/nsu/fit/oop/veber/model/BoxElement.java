package ru.nsu.fit.oop.veber.model;

public class BoxElement {
    private final Coordinate coordinate;
    private final ObjectType objectType;

    public BoxElement(int x, int y, ObjectType objectType) {
        this.coordinate = new Coordinate(x, y);
        this.objectType = objectType;
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

    public ObjectType getObjectType() {
        return objectType;
    }
}
