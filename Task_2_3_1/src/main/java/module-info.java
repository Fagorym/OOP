module ru.nsu.fit.oop.veber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ru.nsu.fit.oop.veber to javafx.fxml;
    exports ru.nsu.fit.oop.veber;
}