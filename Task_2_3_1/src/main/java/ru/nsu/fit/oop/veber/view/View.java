package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.model.BoxElement;

public interface View {

    void endGame();

    void clearScreen();

    void refreshScreen();

    void render(BoxElement boxElement);
}
