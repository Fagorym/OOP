package ru.nsu.fit.oop.veber.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;
import ru.nsu.fit.oop.veber.renderer.ConsoleConverter;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.io.IOException;

public class ConsoleView implements View {

    private final Converter converter;
    private Screen screen;
    private TextGraphics graphics;
    private Terminal terminal;

    public ConsoleView() {
        GameConfiguration gameConfiguration = GameConfiguration.getINSTANCE();
        Presenter presenter = new PresenterImpl(this,
                gameConfiguration.getCONSOLE_SCREEN_HEIGHT(),
                gameConfiguration.getCONSOLE_SCREEN_LENGTH());
        converter = new ConsoleConverter();
        createScene();
        while (true) {
            try {
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null) {
                    presenter.processKeyInput(keyStroke);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            presenter.makeGameStep();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void createScene() {
        try {
            terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(1024, 1280))
                    .createTerminal();
            screen = new TerminalScreen(terminal);
            graphics = terminal.newTextGraphics();
            terminal.setCursorVisible(false);

        } catch (IOException exception) {
            System.out.println("Exception while creating terminal");
        }

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

    @Override
    public void render(BoxElement obj) {
        graphics.drawLine(obj.getX(), obj.getY(), obj.getX(), obj.getY(), (Character) converter.convert(obj).getRepresentation());

    }
}
