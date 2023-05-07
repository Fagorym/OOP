module ru.nsu.fit.oop.veber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.googlecode.lanterna;
    requires com.google.gson;

    opens ru.nsu.fit.oop.veber to javafx.fxml;
    exports ru.nsu.fit.oop.veber.application;
    opens ru.nsu.fit.oop.veber.timer to com.google.gson, javafx.fxml;
    opens ru.nsu.fit.oop.veber.presenter to javafx.fxml;
    opens ru.nsu.fit.oop.veber.view to javafx.fxml;
    opens ru.nsu.fit.oop.veber.utils to com.google.gson, javafx.fxml;


}