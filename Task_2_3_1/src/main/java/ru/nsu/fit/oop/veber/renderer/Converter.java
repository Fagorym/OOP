package ru.nsu.fit.oop.veber.renderer;

import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.utils.Primitive;

public interface Converter {
    Primitive<?> convert(BoxElement element);
}
