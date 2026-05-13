package com.example.proyectojavafx.Dao.impl;

import com.example.proyectojavafx.Dao.DaoUser;
import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoUserImplement implements DaoUser {

    @Override
    public void insertUser(User user) {
        Connection connection = ConexionSingleton.getInstance();
        String sql = "INSERT INTO login VALUES (?, ?);";
        try (PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setString(1, user.getUser());
            pStatement.setString(2, user.getPassword());
            pStatement.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            System.err.println("No se ha ejecutado la sentencia");
        }
    }
}
