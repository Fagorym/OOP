package ru.nsu.fit.oop.veber.renderer;

import ru.nsu.fit.oop.veber.dto.BaseDto;
import ru.nsu.fit.oop.veber.model.BoxElement;

public interface Converter<T extends BaseDto> {
    T convert(BoxElement element);
}
