package com.example.proyectojavafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller2 {
    @FXML
    private Label userText;

    public void setTextA(String s){
        userText.setText("Usuario: " + s);
    }

    @FXML
    public void addVidegame(){

    }
}
