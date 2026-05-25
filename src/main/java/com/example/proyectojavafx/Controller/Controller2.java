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
            System.err.println(e);
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
            Scene scene = new Scene(fxLoad.load(), 500, 600);

            Controller2 c = fxLoad.getController();
            c.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        }catch (NumberFormatException | IOException e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    @FXML
    public void buttonForAdd() {
        try {
            vS.addVideogame(Integer.parseInt(idAdd.getText()), nameAdd.getText(),
                    Double.parseDouble(storageMb.getText()), realaseDate.getValue(),
                    pegi.getValue().toString(), Double.parseDouble(price.getText()));
        } catch (NumberFormatException e) {
            warning();
        }
    }
    //Eliminar
    @FXML
    public void deleteByID() {
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
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    public void buttonForDelete() {
        try {
            vS.deleteVideogame(comboxIds.getValue());
            JOptionPane.showMessageDialog(null, "Se ha ejecutado eso");


        } catch (NullPointerException | NumberFormatException e) {
            warning();
        }
    }

    //Actualizar
    @FXML
    public void updateGame(){
        try {
            FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("update.fxml"));
            Scene scene = new Scene(fxLoad.load(), 500, 500);

            Controller2 c = fxLoad.getController();
            c.inicializate();
            c.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        }catch (NumberFormatException | IOException e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public void buttonForUpdate() {
        try {
            Videogames v1 = dao.searchForID(comboxIds.getValue());
            System.out.println(v1);

            vS.videogameUpdate(comboxIds.getValue(), nameAdd.getText(),
                    Double.parseDouble(storageMb.getText()), v1.getRealaseDate(),
                    pegi.getValue().toString(), Double.parseDouble(price.getText()));
        } catch (RuntimeException e) {
            warning();
        }
    }

    //Buscar
    @FXML
    public void searchID(){
        try {
            FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("idSearch.fxml"));
            Scene scene = new Scene(fxLoad.load(), 500, 500);

            Controller2 c = fxLoad.getController();
            c.inicializate();
            c.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        }catch (NullPointerException ex){
            warning();
        }catch (NumberFormatException | IOException e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public Label caract;

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
            warning();
        }

    }

    @FXML
    public void show(){
        try {
            FXMLLoader fxLoad = new FXMLLoader(ApplicationJava.class.getResource("allShow.fxml"));
            Scene scene = new Scene(fxLoad.load(), 500, 500);

            Controller2 c = fxLoad.getController();
            c.setTextNoUser(userText.getText());
            Stage stage = (Stage) userText.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        }catch (NumberFormatException | IOException e){
            JOptionPane.showMessageDialog(null,e);
        }catch (NullPointerException ex){
            warning();
        }

    }
    @FXML
    private Label all;

    @FXML
    public void lista(){
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
            System.out.println(e);
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
            System.out.println(e);
        }

    }
    public void warning(){
        JOptionPane.showMessageDialog(null, "Faltan datos");
    }


}
