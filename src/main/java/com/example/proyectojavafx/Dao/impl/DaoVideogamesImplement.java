package com.example.proyectojavafx.Dao.impl;

import com.example.proyectojavafx.Dao.DaoVideogames;
import com.example.proyectojavafx.DataBase.ConexionSingleton;
import com.example.proyectojavafx.Models.Videogames;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            JOptionPane.showMessageDialog(null,"Se ha ejecutado la sentencia");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha ejecutado la sentencia / Datos invalidos");
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
            JOptionPane.showMessageDialog(null,"Se ha ejecutado la sentencia (Juego actualizado)");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se ha ejecutado la sentencia (Juego no actualizado)");

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
        List<Videogames> videogamesList = new ArrayList<>();
        Connection conec = ConexionSingleton.getInstance();
        String sql = "SELECT * FROM videogames";
        try (Statement st = conec.createStatement()){
            ResultSet r = st.executeQuery(sql);
            int id = 99;
            String name = " ";
            double storge = 99;
            LocalDate l = null;
            String pegi = " ";
            double price = 99;


            while(r.next()){
                id = r.getInt(1);
                name = r.getString(2);
                storge = r.getDouble(3);
                l = LocalDate.parse(r.getString(4));
                pegi = r.getString(5);
                price = r.getDouble(6);
                videogamesList.add(new Videogames(id,name,storge,l,pegi,price));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("=======================").append("\n");

            for (Videogames v : videogamesList) {
                sb.append(v).append("\n");
                sb.append("=======================").append("\n");
            }
            return sb.toString();


        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
