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
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;

import java.io.IOException;

public class ConsoleView implements View {

    private final Presenter presenter;
    private final int TIMER_TICK = 1000;
    private Screen screen;
    private TextGraphics graphics;
    private Terminal terminal;

    public ConsoleView() {
        this.presenter = new PresenterImpl(this);
        createScene();
        long lastTick = 0;
        while (true) {
            try {
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null) {
                    presenter.processKeyInput(keyStroke.getCharacter());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            presenter.startGameProcess();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void renderFood(Food food) {
        drawObject(food);
    }

    @Override
    public void renderBackground(Box box) {
        for (GeometricalObject cell : box.getCells()) {
            drawObject(cell);
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
        SnakeBlock head = snake.getHeadBlock();
        drawObject(head);

        for (SnakeBlock bodyBlock : snake.getBody()) {
            drawObject(bodyBlock);
        }

        SnakeBlock tail = snake.getTailBlock();
        drawObject(tail);
    }

    private void drawObject(GeometricalObject obj) {
        graphics.drawLine(obj.getX(), obj.getY(), obj.getX(), obj.getY(), obj.getVisualRepresentation());
    }


    @Override
    public void endGame() {
        try {
            terminal.clearScreen();
            graphics.putString(TerminalPosition.TOP_LEFT_CORNER, "GAME IS OVER");
            graphics.putString(new TerminalPosition(0, 1), "PRESS ANY KEY TO EXIT");
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

    @Override
    public void clearScreen() {
        screen.clear();
        try {
            terminal.clearScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void refreshScreen() {
        try {
            terminal.flush();
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
