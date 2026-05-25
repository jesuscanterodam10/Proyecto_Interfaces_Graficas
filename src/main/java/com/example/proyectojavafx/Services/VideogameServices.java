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
    public boolean deleteVideogame (int id){
        if(dao.removeVideogame(id)){
            return true;
        } else
            return false;
    }
    public boolean videogameUpdate (int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        if (dao.updateVideogame(new Videogames(id, name, storage, realaseDate, pegi, price))) {
            return true;
        } else
            return  false;
    }
    public Videogames idSearch(int id){
        return dao.searchForID(id);
    }
    public String showVs() {
        return dao.showAll();
    }
}