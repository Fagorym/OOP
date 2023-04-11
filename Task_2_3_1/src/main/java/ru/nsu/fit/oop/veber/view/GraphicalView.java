package ru.nsu.fit.oop.veber.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.presenter.Presenter;

public class GraphicalView implements View {

    private final Presenter presenter;


    public GraphicalView(Presenter presenter) {
        this.presenter = presenter;
        createUI();
    }

    private void createUI() {

        Button btn1 = new Button("Say, Hello World");
        StackPane root = new StackPane();
        root.getChildren().add(btn1);
        Scene scene = new Scene(root);
    }

    @Override
    public void renderSnake(Snake snake) {

    }

    @Override
    public void gameProcess(Snake snake, Food food, Box box) {

    }

    @Override
    public void endGame() {

    }
}
