package ru.nsu.fit.oop.veber.renderer;

import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.dto.graphical.GraphicalDto;
import ru.nsu.fit.oop.veber.dto.graphical.GraphicalFoodDto;
import ru.nsu.fit.oop.veber.dto.graphical.GraphicalSnakeDto;
import ru.nsu.fit.oop.veber.dto.graphical.GraphicalWallDto;
import ru.nsu.fit.oop.veber.model.BoxElement;

import java.util.ArrayList;
import java.util.List;

public class GraphicalConverter implements Converter<GraphicalDto> {
    private final List<GraphicalDto> dtoList = new ArrayList<>();

    public GraphicalConverter() {
        dtoList.add(new GraphicalSnakeDto(0, 0));
        dtoList.add(new GraphicalFoodDto(0, 0));
        dtoList.add(new GraphicalWallDto(0, 0));

    }

    @Override
    public GraphicalDto convert(BoxElement element) {

        GraphicalDto dto = dtoList.stream()
                .filter(x -> element.getObjectType() == x.getType())
                .findFirst()
                .orElse(new GraphicalDto(0, 0, Color.WHITE));

        GraphicalDto cloneDto = (GraphicalDto) dto.clone();
        cloneDto.setX(element.getX());
        cloneDto.setY(element.getY());
        return cloneDto;
    }
}
