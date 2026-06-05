package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.Dao.DaoVideogames;
import com.example.proyectojavafx.Dao.impl.DaoVideogamesImplement;
import com.example.proyectojavafx.Models.Videogames;

import java.sql.Connection;
import java.time.LocalDate;

public class VideogameServices {
    DaoVideogames dao = new DaoVideogamesImplement();
    public void addWithAutoId(String name, double storage, LocalDate realaseDate, String pegi, double price){
        System.out.println("--- Se ha introducido (//Id autogenerable//) ---");
        System.out.println(dao.insertarAutoIdGame(new Videogames(0, name, storage, realaseDate, pegi, price)));
    }
    public void addVideogame (int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        System.out.println("--- Se ha introducido (//Id asignado manualmente//) ---");
        System.out.println(dao.insertVideogame(new Videogames(id, name, storage, realaseDate, pegi, price)));

    }
    public void deleteVideogame (int id){
        System.out.println("--- Se ha eliminado ---");
        System.out.println(idSearch(id));
        dao.removeVideogame(id);
    }
    public void videogameUpdate (int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        System.out.println("---- Actualizado (Antes) ----");
        System.out.println(dao.searchForID(id));
        System.out.println("---------- (Despues)---------");
        dao.updateVideogame(new Videogames(id, name, storage, realaseDate, pegi, price));
        System.out.println(dao.searchForID(id));
    }
    public Videogames idSearch(int id){
        return dao.searchForID(id);
    }
    public String showVs() {
        return dao.showAll();
    }
}