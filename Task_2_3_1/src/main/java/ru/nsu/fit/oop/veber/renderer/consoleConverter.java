package ru.nsu.fit.oop.veber.renderer;

import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.model.ObjectType;
import ru.nsu.fit.oop.veber.utils.Primitive;

import java.util.HashMap;

public class ConsoleConverter implements Converter {

    private final HashMap<ObjectType, Character> objectsToVisual = new HashMap<>();

    public ConsoleConverter() {
        objectsToVisual.put(ObjectType.FOOD, '@');
        objectsToVisual.put(ObjectType.SNAKE, '$');
        objectsToVisual.put(ObjectType.WALL, '#');
        objectsToVisual.put(ObjectType.NOTHING, ' ');
    }

    @Override
    public Primitive<Character> convert(BoxElement element) {
        return new Primitive<>(objectsToVisual.get(element.getCollisionObject()));
    }
}
