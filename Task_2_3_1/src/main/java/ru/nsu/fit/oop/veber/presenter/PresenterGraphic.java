package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.dto.GraphicalDto;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.renderer.GraphicalConverter;
import ru.nsu.fit.oop.veber.view.GameView;

import java.util.List;

public class PresenterGraphic extends AbstractPresenter implements Presenter {

    private final Converter<GraphicalDto> converter;

    public PresenterGraphic(GameView view) {
        super(view);
        converter = new GraphicalConverter();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<GraphicalDto> getDtoList() {
        return super.getDtoList(converter);
    }


    @Override
    public void stop() {
        timer.stop();
    }
}
