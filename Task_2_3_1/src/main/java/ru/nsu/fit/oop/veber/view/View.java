package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;

public interface View {

    void renderSnake(Snake snake);

    void endGame();

    void clearScreen();

    void refreshScreen();

    void renderFood(Food food);

    void renderBackground(Box box);
}
