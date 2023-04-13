package ru.nsu.fit.oop.veber.model;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private final List<Cell> cells;
    private final int length;
    private final int height;

    public Box(int length, int height) {
        this.length = length;
        this.height = height;
        cells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                for (int j = 0; j < length; j++) {
                    Cell cell = new Cell(j, i);
                    cells.add(cell);
                }
            } else {
                cells.add(new Cell(0, i));
                cells.add(new Cell(length - 1, i));

            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
}
