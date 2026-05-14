module com.example.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.proyectojavafx to javafx.fxml;
    opens com.example.proyectojavafx.Controller to javafx.fxml;

    exports com.example.proyectojavafx;
}