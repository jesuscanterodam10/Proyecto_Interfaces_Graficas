package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckPasswordUser {
    public boolean checkPassword (User use) {
        Connection connection = ConexionSingleton.getInstance();
        String sql = """
                SELECT * FROM login
                WHERE user = ? AND password = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, use.getUser());
            preparedStatement.setString(2, use.getPassword());

            ResultSet rSet = preparedStatement.executeQuery();
            String a = null;
            String b = null;
            while (rSet.next()) {
                a = rSet.getString(1);
                b = rSet.getString(2);
            }
            if (a != null && b != null) {
                return true;
            } else return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
