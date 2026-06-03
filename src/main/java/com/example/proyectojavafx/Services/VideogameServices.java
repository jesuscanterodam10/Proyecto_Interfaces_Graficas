package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.Dao.DaoVideogames;
import com.example.proyectojavafx.Dao.impl.DaoVideogamesImplement;
import com.example.proyectojavafx.Models.Videogames;

import java.time.LocalDate;

public class VideogameServices {
    DaoVideogames dao = new DaoVideogamesImplement();
    public void addVideogame (int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        dao.insertVideogame(new Videogames(id, name, storage, realaseDate, pegi, price));
    }
    public void deleteVideogame (int id){
        dao.removeVideogame(id);
    }
    public void videogameUpdate (int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        dao.updateVideogame(new Videogames(id, name, storage, realaseDate, pegi, price));
    }
    public Videogames idSearch(int id){
        return dao.searchForID(id);
    }
    public String showVs() {
        return dao.showAll();
    }
}