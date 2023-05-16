package ru.nsu.fit.oop.veber.dto.graphical;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.model.ObjectType;

public class GraphicalFoodDto extends GraphicalDto {
    public GraphicalFoodDto(int x, int y) {
        super(x, y, Color.RED);
        type = ObjectType.FOOD;
    }
}
