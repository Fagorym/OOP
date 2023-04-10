package ru.nsu.fit.oop.veber.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import ru.nsu.fit.oop.veber.presenter.Presenter;

import javafx.stage.Stage;

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
}
