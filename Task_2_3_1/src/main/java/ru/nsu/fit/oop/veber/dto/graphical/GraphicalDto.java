package ru.nsu.fit.oop.veber.dto.graphical;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.dto.BaseDto;
import ru.nsu.fit.oop.veber.model.ObjectType;

public class GraphicalDto extends BaseDto {
    private final int blockSize = 10;
    private Color color;

    protected ObjectType type;

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

    public void render(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(
                this.getX() * blockSize,
                this.getY() * blockSize,
                blockSize,
                blockSize
        );

    }

    public ObjectType getType() {
        return type;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
