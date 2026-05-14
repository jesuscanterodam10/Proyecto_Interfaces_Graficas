package com.example.proyectojavafx.Dao.impl;

import com.example.proyectojavafx.Dao.DaoVideogames;
import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.Videogames;

import java.sql.*;
import java.time.LocalDate;

public class DaoVideogamesImplement implements DaoVideogames {
    Connection connection = ConexionSingleton.getInstance();
    String sql;
    @Override
    public void insertVideogame(Videogames videogame) {
        sql = "INSERT INTO videogames VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, videogame.getId());
            pStatement.setString(2, videogame.getName());
            pStatement.setDouble(3, videogame.getStorage());
            pStatement.setString(4, videogame.getRealaseDate().toString());
            pStatement.setString(5, videogame.getPegi());
            pStatement.setDouble(6, videogame.getPrice());
            pStatement.executeUpdate();
            System.out.println("Sentencia de insertar videojuegos ejecutada de manera correcta");
        } catch (SQLException e) {
            System.err.println("No se ha ejecutado la sentencia");
        }
    }

    @Override
    public boolean removeVideogame(int id) {
        sql = "DELETE FROM videogames WHERE id = ?;";
        try (PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, id);
            pStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Sentencia delete no ejecutada");
            return false;
        }
    }

    @Override
    public boolean updateVideogame(Videogames videogame) {
        sql = """ 
                UPDATE videogames SET 
                name = ?,
                storage = ?,
                pegi = ?,
                price = ? 
                WHERE id = ?;
                """;
        try (PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setString(1, videogame.getName());
            pStatement.setDouble(2, videogame.getStorage());
            pStatement.setString(3, videogame.getPegi());
            pStatement.setDouble(4, videogame.getPrice());
            pStatement.setInt(5, videogame.getId());
            pStatement.executeUpdate();
            System.out.println("Actualizando el videojuego");
            return true;
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el videojuego");
            return false;
        }
    }

    @Override
    public Videogames searchForID(int id) {
        Videogames videogame = null;
        sql = "SELECT * FROM videogames WHERE id = ?;";
        try (PreparedStatement pStatement = connection.prepareStatement(sql)) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                String name = rSet.getString(2);
                double storage = rSet.getDouble(3);
                String date = rSet.getString(4);
                LocalDate dateF = LocalDate.parse(date);
                String pegi = rSet.getString(5);
                double price = rSet.getDouble(6);
                videogame = new Videogames(id, name, storage, dateF, pegi, price);
            }
            return videogame;
        } catch (SQLException e) {
            System.err.println("No se ha podido encontrar el videojuego con ese ID");
        }
        return null;
    }

    @Override
    public String showAll() {
        sql = "SELECT * FROM videogames;";
        try (Statement statement = connection.createStatement()) {
           return statement.executeQuery(sql).toString();
        } catch (SQLException e) {
            System.err.println("No se ha podido mostrar la informacion acerca del videojuego");
        }
        return null;
    }
}
