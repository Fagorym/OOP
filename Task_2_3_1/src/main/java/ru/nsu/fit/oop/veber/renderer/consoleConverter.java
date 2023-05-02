package ru.nsu.fit.oop.veber.renderer;

import ru.nsu.fit.oop.veber.dto.ConsoleDto;
import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.model.ObjectType;

import java.util.HashMap;

public class ConsoleConverter implements Converter<ConsoleDto> {

    private final HashMap<ObjectType, Character> objectsToVisual = new HashMap<>();

    public ConsoleConverter() {
        objectsToVisual.put(ObjectType.FOOD, '@');
        objectsToVisual.put(ObjectType.SNAKE, '$');
        objectsToVisual.put(ObjectType.WALL, '#');
        objectsToVisual.put(ObjectType.NOTHING, ' ');
    }

    @Override
    public ConsoleDto convert(BoxElement element) {
        return new ConsoleDto(element.getX(), element.getY(), objectsToVisual.get(element.getObjectType()));
    }
}
