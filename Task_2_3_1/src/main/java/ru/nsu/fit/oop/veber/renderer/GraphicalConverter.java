package ru.nsu.fit.oop.veber.renderer;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.dto.GraphicalDto;
import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.model.ObjectType;

import java.util.HashMap;
import java.util.Map;

public class GraphicalConverter implements Converter<GraphicalDto> {
    private final Map<ObjectType, Color> objectTypeColorMap = new HashMap<>();

    public GraphicalConverter() {
        objectTypeColorMap.put(ObjectType.SNAKE, Color.GREEN);
        objectTypeColorMap.put(ObjectType.FOOD, Color.RED);
        objectTypeColorMap.put(ObjectType.NOTHING, Color.WHITE);
        objectTypeColorMap.put(ObjectType.WALL, Color.GREY);
    }

    @Override
    public GraphicalDto convert(BoxElement element) {
        return new GraphicalDto(element.getX(), element.getY(), objectTypeColorMap.get(element.getObjectType()));
    }
}
