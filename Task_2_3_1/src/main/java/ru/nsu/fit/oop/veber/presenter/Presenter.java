package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.dto.BaseDto;

import java.util.List;

/**
 * Abstract presenter interface.
 */
public interface Presenter {
    /**
     * Processing key input for graphical view.
     *
     * @param event instance of key event.
     */
    void processKeyInput(KeyEvent event);

    /**
     * Processing key input for console view.
     *
     * @param stroke instance of key stroke.
     */
    void processKeyInput(KeyStroke stroke);

    /**
     * Function, that make game step.
     */
    void makeGameStep();

    /**
     * Function, that starts the game logic.
     */
    void start();

    /**
     * Getting dto list to render.
     *
     * @param <T> extends BaseDto
     * @return List of T, where T extends BaseDto (e.g. GraphicalDto, ConsoleDto)
     */
    <T extends BaseDto> List<T> getDtoList();

    /**
     * Function, that stops the game logic.
     */
    @Deprecated
    void stop();
}
