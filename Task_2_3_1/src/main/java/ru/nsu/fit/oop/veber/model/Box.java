package ru.nsu.fit.oop.veber.model;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private final List<Cell> cells;

    public Box(int length, int height) {
        cells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                for (int j = 0; j < length; j++) {
                    Cell cell = new Cell(i, j);
                    cells.add(cell);
                }
            } else {
                cells.add(new Cell(i, 0));
                cells.add(new Cell(i, length - 1));

            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }
}
