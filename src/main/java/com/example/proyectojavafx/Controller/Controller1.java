package com.example.proyectojavafx.Controller;

import com.example.proyectojavafx.ApplicationJava;
import com.example.proyectojavafx.Models.User;
import com.example.proyectojavafx.Services.CheckPasswordUser;
import com.example.proyectojavafx.Services.CheckUser;
import com.example.proyectojavafx.Services.UserServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller1 {
    CheckUser checkUser = new CheckUser();
    CheckPasswordUser checkPasswordUser = new CheckPasswordUser();
    UserServices userServices = new UserServices();

    @FXML
    private Button admin;

    @FXML
    private Label confirm;

    @FXML
    private TextField introUser;

    @FXML
    private PasswordField introPassword;

    @FXML
    private TextField introUserA;

    @FXML
    private PasswordField introPasswordA;

    int tries = 0;

    @FXML
    public void logButton(){
        boolean warning = false;
        if((introUser.getText().toLowerCase().contains("select") || introUser.getText().toLowerCase().contains("from")) || (introPassword.getText().toLowerCase().contains("select") || introPassword.getText().toLowerCase().contains("from"))){
            confirm.setText("No");
            cleaner();
            warning = true;
        }

        boolean userCondition = checkUser.checkUser(introUser.getText());
        boolean passwordCondition = checkPasswordUser.checkPassword(new User(introUser.getText(), introPassword.getText()));


        if (userCondition && passwordCondition){
         try {
             tries = 0;
             confirm.setText("Usuario correcto");

             FXMLLoader loaderXML =new FXMLLoader(ApplicationJava.class.getResource("menu.fxml"));
             Scene scne = new Scene(loaderXML.load(),500,550);

             Controller2 controller2 = loaderXML.getController();

             controller2.setTextA(introUser.getText());
             Stage stage = (Stage) confirm.getScene().getWindow();
             stage.setResizable(false);

             stage.setScene(scne);
             stage.show();


         } catch (IOException e) {
             System.err.println(e);
         }
        }
        else if (tries > 6){
            confirm.setText("Hay un boton para añadir usuario... por si no te has dado cuenta... (Usuario incorrecto)");
        }
        else if (!warning){
            confirm.setText("Usuario o contraseña incorrecto");
            tries++;
        }



        if ((introUser.getText().isEmpty() || introPassword.getText().isEmpty()) && !warning){
            confirm.setText("...? ¿Es esto algun tipo de broma?");
            tries++;
        }
        if (introUser.getText().isEmpty() && !warning){
            confirm.setText("Hey... ¿Como pones una contraseña antes que un usuario?");
            tries++;
        }
        if(introPassword.getText().isEmpty() && !warning){
            confirm.setText("¿Usuario sin contraseña? Ojala...");
            tries++;
        }
        if ((introUser.getText().isEmpty() && introPassword.getText().isEmpty()) && !warning){
            confirm.setText("...? ¿Es esto algun tipo de broma?");
            tries++;
        }

    }
    @FXML
    private void adminSecret(){
        try {
            FXMLLoader fLoader = new FXMLLoader(ApplicationJava.class.getResource("AdminAdder.fxml"));
            Scene scn = new Scene(fLoader.load(), 500, 500);

            Stage stage = (Stage) confirm.getScene().getWindow();
            stage.setResizable(false);

            stage.setScene(scn);
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void close(){
        Platform.exit();
    }

    public void addUser(){
        if (introUserA.getText().toLowerCase().contains("select") || introUserA.getText().toLowerCase().contains("from") || (introPasswordA.getText().toLowerCase().contains("select") || introPasswordA.getText().toLowerCase().contains("from"))) {
            confirm.setText("No");
            cleanerA();
        }
        else{
                if (introUserA.getText().isEmpty() || introPasswordA.getText().isEmpty()) {
                    confirm.setText("Como que falta algo, ¿no?");
                } else {
                    if (userServices.adder(introUserA.getText(), introPasswordA.getText())) {
                        confirm.setText("Usuario añadido");
                        System.out.println("Se ha añadido -- Usuario: " + introUserA.getText() + " Contraseña: " + introPasswordA.getText());
                        cleanerA();
                    } else {
                        confirm.setText("Usuario no añadido");
                    }
                }
        }

    }

    public void back(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationJava.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 540);

            Stage stage = (Stage) confirm.getScene().getWindow();
            stage.setResizable(false);


            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void cleanerA(){
        introUserA.clear();
        introPasswordA.clear();
    }

    public void cleaner(){
        if(!introUser.getText().isEmpty()) {
            introUser.clear();
        }
        if(!introPassword.getText().isEmpty()) {
            introPassword.clear();
        }
    }

}
