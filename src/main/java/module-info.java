module com.example.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectojavafx to javafx.fxml;
    exports com.example.proyectojavafx;
}