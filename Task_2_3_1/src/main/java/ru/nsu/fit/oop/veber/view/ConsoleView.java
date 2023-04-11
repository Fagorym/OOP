package ru.nsu.fit.oop.veber.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.presenter.Presenter;

import java.io.IOException;

public class ConsoleView implements View {

    private final Presenter presenter;
    private final int SCENE_LENGTH = 60;
    private final int SCENE_HEIGHT = 20;

    private Screen screen;
    private TextGraphics graphics;

    private Terminal terminal;

    public ConsoleView(Presenter presenter) {
        this.presenter = presenter;
        createScene();
    }

    private void renderFood(Food food) {
        graphics.drawLine(food.getX(), food.getY(), food.getX(), food.getY(), '@');
    }

    private void renderField() {
        graphics.drawLine(0, 0, SCENE_LENGTH, 0, '#');
        for (int i = 1; i < SCENE_HEIGHT; i++) {
            graphics.drawLine(0, i, 1, i, '#');
            graphics.drawLine(SCENE_LENGTH - 1, i, SCENE_LENGTH, i, '#');
        }
        graphics.drawLine(0, SCENE_HEIGHT, SCENE_LENGTH, SCENE_HEIGHT, '#');
    }

    private void createScene() {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            graphics = terminal.newTextGraphics();
            terminal.setCursorVisible(false);

        } catch (IOException exception) {
            System.out.println("Exception while creating terminal");
        }

    }

    @Override
    public void renderSnake(Snake snake) {
        graphics.drawLine(snake.getX(), snake.getY(), snake.getX(), snake.getY(), '$');
        for (Snake tailBlock : snake.getTail()) {
            graphics.drawLine(tailBlock.getX(), tailBlock.getY(), tailBlock.getX(), tailBlock.getY(), '$');

        }
    }


    public void gameProcess(Snake snake, Food food) {
        try {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                presenter.processKeyInput(keyStroke.getCharacter());
            }
            screen.clear();
            terminal.clearScreen();
            renderField();
            snake.update();
            renderFood(food);
            renderSnake(snake);
            terminal.flush();
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
