package ru.nsu.fit.oop.veber.renderer;

import ru.nsu.fit.oop.veber.dto.BaseDto;
import ru.nsu.fit.oop.veber.model.BoxElement;

/**
 * Interface for converter from model to dto.
 *
 * @param <T> dto class that extends base dto.
 */
public interface Converter<T extends BaseDto> {
    /**
     * Converting function for the model of the game.
     *
     * @param element model entity
     * @return generic class T that extends Base Dto.
     */
    T convert(BoxElement element);
}
