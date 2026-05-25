package com.example.proyectojavafx.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    public static void main(String[] args) {

        Connection connection = ConexionSingleton.getInstance();
        /*String sql = """
                        CREATE TABLE login (
                        user TEXT NOT NULL PRIMARY KEY,
                        password TEXT NOT NULL
                        );
                     """;

        try (Statement statement = connection.createStatement()) {
            //statement.executeUpdate(sql);
            System.out.println("Comando ejecutado correctamente");
        } catch (SQLException e) {
            System.err.println("Comando no ejecutado");
        }
*/
         String sql = """
               CREATE TABLE videogames (
               id NUMERIC NOT NULL PRIMARY KEY check (id > -1),
               name TEXT NOT NULL,
               storage NUMERIC NOT NULL check (id >= 000.1),
               realaseDate TEXT NOT NULL,
               pegi TEXT CHECK ( pegi IN ('18', '16', '12', '7', '3', 'TP')) NOT NULL,
               price NUMERIC NOT NULL check ( price > -1 )
               );
               """;
        //String sql = "DROP TABLE videogames;";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Comando ejecutado correctamente");
        } catch (SQLException e) {
            System.err.println("Comando no ejecutado");
        }
    }
}
