package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;

public interface View {
    void renderSnake(Snake snake);

    void gameProcess(Snake snake, Food food, Box box);

    void endGame();
}
