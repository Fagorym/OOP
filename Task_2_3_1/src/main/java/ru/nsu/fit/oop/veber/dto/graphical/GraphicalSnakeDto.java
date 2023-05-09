package ru.nsu.fit.oop.veber.dto.graphical;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.model.ObjectType;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class GraphicalSnakeDto extends GraphicalDto {
    public GraphicalSnakeDto(int x, int y) {
        super(x, y, GameConfiguration.getGameConfiguration().getSnakeColor());
        type = ObjectType.SNAKE;
    }

    @Override
    public void render(GraphicsContext context) {
        super.render(context);

        context.setFill(Color.ORANGE);
        context.fillRect(
                getX() * getBlockSize(),
                getY() * getBlockSize(),
                5,
                5);
    }

}
