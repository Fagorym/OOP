package ru.nsu.fit.oop.veber.dto.graphical;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.model.ObjectType;

public class GraphicalWallDto extends GraphicalDto {
    public GraphicalWallDto(int x, int y) {
        super(x, y, Color.GREY);
        type = ObjectType.WALL;
    }
}
