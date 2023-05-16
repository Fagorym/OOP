package ru.nsu.fit.oop.veber.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Box {
    private final List<Wall> walls;
    private final int length;
    private final int height;

    public Box(int length, int height) {
        this.length = length;
        this.height = height;
        walls = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                for (int j = 0; j < length; j++) {
                    Wall wall = new Wall(j, i);
                    walls.add(wall);
                }
            } else {
                walls.add(new Wall(0, i));
                walls.add(new Wall(length - 1, i));

            }
        }
    }

    public void addWalls(Collection<Wall> walls) {
        this.walls.addAll(walls);
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
}
