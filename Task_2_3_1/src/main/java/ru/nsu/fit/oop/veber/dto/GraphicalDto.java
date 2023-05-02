package ru.nsu.fit.oop.veber.dto;

import javafx.scene.paint.Color;

public class GraphicalDto extends BaseDto {
    private final int blockSize = 10;
    private final Color color;

    public GraphicalDto(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getBlockSize() {
        return blockSize;
    }
}
