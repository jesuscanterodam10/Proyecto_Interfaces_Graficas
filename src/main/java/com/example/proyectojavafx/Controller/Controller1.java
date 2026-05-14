package com.example.proyectojavafx.Controller;

import com.example.proyectojavafx.ApplicationJava;
import com.example.proyectojavafx.Models.User;
import com.example.proyectojavafx.Services.CheckPasswordUser;
import com.example.proyectojavafx.Services.CheckUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller1 {
    CheckUser checkUser = new CheckUser();
    CheckPasswordUser checkPasswordUser = new CheckPasswordUser();

    @FXML
    private Label confirm;

    @FXML
    private TextField introUser;

    @FXML
    private PasswordField introPassword;

    @FXML
    public void logButton(){
        boolean userCondition = checkUser.checkUser(introUser.getText());
        boolean passwordCondition = checkPasswordUser.checkPassword(new User(introUser.getText(), introPassword.getText()));

        if (userCondition && passwordCondition){
         try {
             confirm.setText("Usuario correcto");

             FXMLLoader loaderXML =new FXMLLoader(ApplicationJava.class.getResource("menu.fxml"));
             Scene scne = new Scene(loaderXML.load(),400,800);

             Controller2 controller2 = loaderXML.getController();

             controller2.setTextA(introUser.getText());
             Stage stage = (Stage) confirm.getScene().getWindow();

             stage.setScene(scne);
             stage.show();


         } catch (IOException e) {
             System.err.println(e);
         }
        }
        else {confirm.setText("Usuario incorrecto");}
    }
}
