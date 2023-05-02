package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.dto.BaseDto;

import java.util.List;

public interface Presenter {
    void processKeyInput(KeyEvent event);

    void processKeyInput(KeyStroke stroke);

    void makeGameStep();

    void start();

    <T extends BaseDto> List<T> getDtoList();
}
