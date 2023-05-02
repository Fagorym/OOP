package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.dto.ConsoleDto;
import ru.nsu.fit.oop.veber.renderer.ConsoleConverter;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.view.View;

import java.util.List;

public class PresenterConsole extends AbstractPresenter implements Presenter {

    private final Converter<ConsoleDto> converter;

    public PresenterConsole(View view) {
        super(view);
        this.converter = new ConsoleConverter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ConsoleDto> getDtoList() {
        return super.getDtoList(converter);
    }
}
