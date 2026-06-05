package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.Videogames;

import java.sql.*;
import java.time.LocalDate;

public class DataServiceController {
    static Connection connection = ConexionSingleton.getInstance();
    public static boolean dataReviser(){
        String sql = "SELECT * FROM videogames;";
        try (Statement pStatement = connection.createStatement()) {
            ResultSet rSet = pStatement.executeQuery(sql);

            Videogames videogame = null;

            while (rSet.next()) {
                int id = rSet.getInt(1);
                String name = rSet.getString(2);
                double storage = rSet.getDouble(3);
                String date = rSet.getString(4);
                LocalDate dateF = LocalDate.parse(date);
                String pegi = rSet.getString(5);
                double price = rSet.getDouble(6);
                videogame = new Videogames(id, name, storage, dateF, pegi, price);
            }

            if(videogame != null){return true;}

        }catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(dataReviser());
    }
}
