package com.example.proyectojavafx.Controller;

import com.example.proyectojavafx.ApplicationJava;
import com.example.proyectojavafx.Dao.impl.DaoVideogamesImplement;
import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.Videogames;
import com.example.proyectojavafx.Services.VideogameServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.proyectojavafx.Services.DataServiceController.dataReviser;


public class Controller2 {
    VideogameServices vS = new VideogameServices();
    Connection conne = ConexionSingleton.getInstance();
    DaoVideogamesImplement dao = new DaoVideogamesImplement();

    @FXML
    private Label userText;

    public void setTextA(String s){
        userText.setText("Usuario: " + s);
    }
    public void setTextNoUser(String s){
        userText.setText(s);
    }


    @FXML
    private TextField idAdd;

    @FXML
    private TextField nameAdd;

    @FXML
    private TextField storageMb;

    @FXML
    private DatePicker realaseDate;

    @FXML
    private ComboBox pegi;

    @FXML
    private TextField price;

    @FXML
    private ComboBox<Integer> comboxIds;

    @FXML
    public Label caract;

    @FXML
    private Label all;

    public void idShow(){
        ComboBox<Integer> idContainer = new ComboBox<>();
        List<Integer> i = new ArrayList<>();
        String sql = """
                     SELECT id FROM videogames;
                     """;
        try (Statement st = conne.createStatement()){
            ResultSet setR = st.executeQuery(sql);
            while(setR.next()){
                i.add(setR.getInt(1));
            }
            idContainer.setItems(FXCollections.observableArrayList(i));
            comboxIds.setItems(idContainer.getItems());
        } catch (SQLException | RuntimeException e) {
            warning(4);
        }
    }
    public void inicializate(){
        idShow();
    }


    //Añadir
    @FXML
    public void addVideogame(){
        try {

            FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("add.fxml"));
            Scene scene = new Scene(fxLoad.load(), 1600, 200);

            Controller2 c = fxLoad.getController();
            c.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();

            stage.setScene(scene);
            stage.show();


        }catch (NumberFormatException | IOException e){
            warning(2);
        }

    }
    @FXML
    public void buttonForAdd() {

        try {
            vS.addVideogame(Integer.parseInt(idAdd.getText()), nameAdd.getText(),
                    Double.parseDouble(storageMb.getText()), realaseDate.getValue(),
                    pegi.getValue().toString(), Double.parseDouble(price.getText()));
        } catch (RuntimeException e) {
            warning(1);
        }
    }
    //Eliminar
    @FXML
    public void deleteByID() {
        if (dataReviser()) {
            try {
                FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("delete.fxml"));
                Scene scene = new Scene(fxLoad.load(), 400, 300);

                Controller2 c = fxLoad.getController();
                c.inicializate();
                c.setTextNoUser(userText.getText());
                Stage stage = (Stage) userText.getScene().getWindow();

                stage.setScene(scene);
                stage.show();

            } catch (NumberFormatException | IOException e) {
                warning(2);
            }
        }else {
            warning(3);
        }
    }
    @FXML
    public void buttonForDelete() {
        try {
            vS.deleteVideogame(comboxIds.getValue());
            JOptionPane.showMessageDialog(null, "Se ha eliminado el dato seleccionado");
            idShow();
            if (!dataReviser()) {
                try {
                    FXMLLoader loaderXML =new FXMLLoader(ApplicationJava.class.getResource("menu.fxml"));
                    Scene scne = new Scene(loaderXML.load(),500,550);

                    Controller2 c = loaderXML.getController();

                    c.setTextNoUser(userText.getText());
                    Stage stage = (Stage) userText.getScene().getWindow();

                    stage.setScene(scne);
                    stage.show();


                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (NullPointerException | NumberFormatException e) {
            warning(1);
        }
    }

    //Actualizar
    @FXML
    public void updateGame(){
        if(dataReviser()) {
            try {
                FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("update.fxml"));
                Scene scene = new Scene(fxLoad.load(), 500, 500);

                Controller2 c = fxLoad.getController();
                c.inicializate();
                c.setTextNoUser(userText.getText());
                Stage stage = (Stage) userText.getScene().getWindow();

                stage.setScene(scene);
                stage.show();

            } catch (NumberFormatException | IOException e) {
                warning(4);
            }
        }else {
            warning(3);
        }
    }

    @FXML
    public void buttonForUpdate() {
        try {
            Videogames v1 = dao.searchForID(comboxIds.getValue());
            System.out.println(v1);

            if(Double.parseDouble(storageMb.getText()) <= 0){
                throw new NumberFormatException();
            }

            vS.videogameUpdate(comboxIds.getValue(), nameAdd.getText(),
                    Double.parseDouble(storageMb.getText()), v1.getRealaseDate(),
                    pegi.getValue().toString(), Double.parseDouble(price.getText()));
        } catch (NumberFormatException e) {
            warning(2);
        }
        catch (RuntimeException e){
            warning(1);
        }
    }

    //Buscar
    @FXML
    public void searchID(){
        if (dataReviser()) {
            try {
                FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("idSearch.fxml"));
                Scene scene = new Scene(fxLoad.load(), 500, 500);

                Controller2 c = fxLoad.getController();
                c.inicializate();
                c.setTextNoUser(userText.getText());
                Stage stage = (Stage) userText.getScene().getWindow();

                stage.setScene(scene);
                stage.show();

            } catch (NullPointerException ex) {
                warning(1);
            } catch (NumberFormatException | IOException e) {
                warning(2);
            }
        }else {
            warning(3);
        }
    }



    @FXML
    public void buttonForID() {
        try {
            Videogames v1 = vS.idSearch(comboxIds.getValue());
            try {
                caract.setText(v1.toString());
            } catch (Exception e) {
                caract.setText("No se encuentra");
            }
        } catch (NullPointerException e) {
            warning(1);
        }

    }

    @FXML
    public void show(){
        if(dataReviser()) {
            try {
                FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("allShow.fxml"));
                Scene scene = new Scene(fxLoad.load(), 500, 500);

                Controller2 c = fxLoad.getController();
                c.setTextNoUser(userText.getText());
                Stage stage = (Stage) userText.getScene().getWindow();

                stage.setScene(scene);
                stage.show();

            } catch (NumberFormatException | IOException e) {
                warning(4);
            } catch (NullPointerException ex) {
                warning(1);
            }
        }else {
            warning(3);
        }
    }

    @FXML
    public void list(){
        all.setText(vS.showVs());
    }
    @FXML
    public void backButton(){
        try {
            FXMLLoader fLoader = new FXMLLoader(ApplicationJava.class.getResource("login.fxml"));
            Scene scene = new Scene(fLoader.load(),500,500);

            Controller1 c1 = fLoader.getController();
            Stage stage = (Stage) userText.getScene().getWindow();


            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            warning(4);
        }

    }
    @FXML
    public void backButtonMenu(){
        try {
            FXMLLoader fLoader = new FXMLLoader(ApplicationJava.class.getResource("menu.fxml"));
            Scene scene = new Scene(fLoader.load(),500,550);

            Controller2 c2 = fLoader.getController();
            c2.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();


            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            warning(4);
        }

    }
    public void warning(int errorNumber){
        switch (errorNumber) {
            case 1 -> JOptionPane.showMessageDialog(null, "Faltan datos");
            case 2 -> JOptionPane.showMessageDialog(null, "Datos con formato no valido");
            case 3 -> JOptionPane.showMessageDialog(null, "No hay datos, añade antes de hacer algo");
            case 4 -> JOptionPane.showMessageDialog(null, "Esto no estaba previsto");
            default -> JOptionPane.showMessageDialog(null, "No debereia ver este mensaje");
        }
    }

    public void numberRandom(){
        String sql = "SELECT id FROM videogames";
        try (Statement st = conne.createStatement()){
            Random random = new Random();
            int i = random.nextInt(2147483646) + 1;

            idAdd.setText(String.valueOf(i));


        }catch (SQLException e){

        }

    }



}
