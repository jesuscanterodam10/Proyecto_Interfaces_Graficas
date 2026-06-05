package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUser {
    public boolean checkUser (String use) {
        Connection connection = ConexionSingleton.getInstance();
        String sql = """
                SELECT user FROM login
                WHERE user = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,use);

            ResultSet rSet = preparedStatement.executeQuery();
            String a = null;
            while (rSet.next()){
            a = rSet.getString(1);
            }
            if (a != null)
            {return true;}
            else return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}
