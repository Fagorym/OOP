package ru.nsu.fit.oop.veber.model;

public class GeometricalObject {
    private final Coordinate coordinate;

    public GeometricalObject(int x, int y) {
        this.coordinate = new Coordinate(x, y);
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
}
