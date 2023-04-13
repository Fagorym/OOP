package ru.nsu.fit.oop.veber.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.presenter.Presenter;

import java.io.IOException;

public class ConsoleView implements View {

    private final Presenter presenter;

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

    private void renderField(Box box) {
        for (GeometricalObject cell : box.getCells()) {
            graphics.drawLine(cell.getX(), cell.getY(), cell.getX(), cell.getY(), '#');
        }
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
        for (SnakeBlock tailBlock : snake.getTail()) {
            graphics.drawLine(tailBlock.getX(), tailBlock.getY(), tailBlock.getX(), tailBlock.getY(), '$');

        }
    }


    public void gameProcess(Snake snake, Food food, Box box) {
        try {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                presenter.processKeyInput(keyStroke.getCharacter());
            }
            screen.clear();
            terminal.clearScreen();
            renderField(box);
            snake.update();
            renderFood(food);
            renderSnake(snake);
            terminal.flush();
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void endGame() {
        try {
            terminal.clearScreen();
            graphics.putString(TerminalPosition.TOP_LEFT_CORNER, "GAME IS OVER");
            graphics.putString(TerminalPosition.OFFSET_1x1, "PRESS ANY KEY TO EXIT");
            terminal.flush();
            screen.refresh();
            KeyStroke keyStroke;
            do {
                keyStroke = screen.pollInput();

            } while (keyStroke == null);
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
