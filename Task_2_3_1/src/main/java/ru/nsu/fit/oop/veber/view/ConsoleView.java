package ru.nsu.fit.oop.veber.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.nsu.fit.oop.veber.dto.ConsoleDto;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterConsole;
import ru.nsu.fit.oop.veber.timer.ConsoleTimer;
import ru.nsu.fit.oop.veber.timer.Timer;

import java.io.IOException;
import java.util.List;

public class ConsoleView implements GameView {

    private Screen screen;
    private TextGraphics graphics;
    private Terminal terminal;

    private final Presenter presenter;

    public ConsoleView() {
        presenter = new PresenterConsole(this);
        createScene();
        presenter.start();
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
    public void render() {
        List<ConsoleDto> consoleDtoList = presenter.getDtoList();
        consoleDtoList.forEach(this::renderDto);
        try {
            terminal.flush();
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void renderDto(ConsoleDto dto) {
        graphics.drawLine(dto.getX(), dto.getY(), dto.getX(), dto.getY(), dto.getSymbol());

    }

    @Override
    @SuppressWarnings("busy-waiting")
    public Timer setTimer(Runnable step) {
        return new ConsoleTimer(new Thread(() -> {
            while (true) {
                try {
                    KeyStroke keyStroke = screen.pollInput();
                    if (keyStroke != null) {
                        presenter.processKeyInput(keyStroke);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    step.run();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }
}
