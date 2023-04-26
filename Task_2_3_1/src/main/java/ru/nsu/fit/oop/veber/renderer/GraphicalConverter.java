package ru.nsu.fit.oop.veber.renderer;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.model.ObjectType;
import ru.nsu.fit.oop.veber.utils.Primitive;

import java.util.HashMap;
import java.util.Map;

public class GraphicalConverter implements Converter {
    private final Map<ObjectType, Color> objectTypeColorMap = new HashMap<>();

    public GraphicalConverter() {
        objectTypeColorMap.put(ObjectType.SNAKE, Color.GREEN);
        objectTypeColorMap.put(ObjectType.FOOD, Color.RED);
        objectTypeColorMap.put(ObjectType.NOTHING, Color.WHITE);
        objectTypeColorMap.put(ObjectType.WALL, Color.GREY);
    }

    @Override
    public Primitive<?> convert(BoxElement element) {
        return new Primitive<>(objectTypeColorMap.get(element.getObjectType()));
    }
}
