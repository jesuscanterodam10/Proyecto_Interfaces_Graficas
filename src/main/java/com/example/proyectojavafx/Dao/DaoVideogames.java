package com.example.proyectojavafx.Dao;

import com.example.proyectojavafx.Models.Videogames;

public interface DaoVideogames {
    void insertVideogame(Videogames videogame);
    boolean removeVideogame(int id);
    boolean updateVideogame(Videogames videogame);
    Videogames searchForID(int id);
    String showAll();
}
