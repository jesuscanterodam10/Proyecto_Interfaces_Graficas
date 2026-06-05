package com.example.proyectojavafx.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {
    private static final String url = "jdbc:sqlite:BD/data.db";
    private static Connection connection;

    public ConexionSingleton() {
    }

    public static Connection getInstance() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url);
                System.out.println("Conexion Correcta con la BD");
            }
        } catch (SQLException e) {
            System.err.println("Conexion Incorrecta con la BD");
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("No se ha podido cerrar la BD");
        }
    }
}
