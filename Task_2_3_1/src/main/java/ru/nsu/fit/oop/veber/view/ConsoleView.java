package ru.nsu.fit.oop.veber.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.nsu.fit.oop.veber.model.Snake;

import java.io.IOException;

public class ConsoleView implements View {

    private final int SCENE_LENGTH = 60;
    private final int SCENE_HEIGHT = 15;

    private TextGraphics graphics;

    public ConsoleView() {
        createScene();
        renderField();
        renderSnake(new Snake(10, 4));
    }

    private void renderField() {
    }

    private void createScene() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            graphics = terminal.newTextGraphics();
            graphics.drawLine(0, 0, SCENE_LENGTH, 0, '#');
            for (int i = 1; i < SCENE_HEIGHT; i++) {
                graphics.drawLine(0, i, 1, i, '#');
                graphics.drawLine(SCENE_LENGTH - 1, i, SCENE_LENGTH, i, '#');
            }
            graphics.drawLine(0, SCENE_HEIGHT, SCENE_LENGTH, SCENE_HEIGHT, '#');
            terminal.flush();
        } catch (IOException exception) {
            System.out.println("Exception while creating terminal");
        }

    }

    @Override
    public void renderSnake(Snake snake) {
        graphics.drawLine(snake.getX(), snake.getY(), snake.getX(), snake.getY(), '$');
    }
}
