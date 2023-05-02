package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.dto.GraphicalDto;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.renderer.GraphicalConverter;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.View;

import java.util.List;

public class PresenterGraphic extends AbstractPresenter implements Presenter {

    private final Converter<GraphicalDto> converter;

    public PresenterGraphic(View view) {
        super(view);
        converter = new GraphicalConverter();
    }

    @Override
    public void processKeyInput(KeyEvent event) {
        Direction direction = switch (event.getCode()) {
            case RIGHT, D -> Direction.RIGHT;
            case LEFT, A -> Direction.LEFT;
            case UP, W -> Direction.UP;
            case DOWN, S -> Direction.DOWN;
            default -> null;
        };
        super.snake.setDirection(direction);
    }

    @Override
    public void processKeyInput(KeyStroke stroke) {
        Direction direction = switch (stroke.getCharacter()) {
            case 'W' -> Direction.UP;
            case 'D' -> Direction.RIGHT;
            case 'S' -> Direction.DOWN;
            case 'A' -> Direction.LEFT;
            default -> null;
        };
        snake.setDirection(direction);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GraphicalDto> getDtoList() {
        return super.getDtoList(converter);
    }


}
