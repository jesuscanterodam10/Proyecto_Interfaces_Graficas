package com.example.proyectojavafx.Models;

import java.time.LocalDate;
import java.util.Objects;

public class Videogames {
    private final int id;
    private String name;
    private double storage;
    private LocalDate realaseDate;
    private String  pegi;
    private double price;

    public Videogames(int id, String name, double storage, LocalDate realaseDate, String pegi, double price) {
        this.id = id;
        this.name = name;
        this.storage = storage;
        this.realaseDate = realaseDate;
        this.pegi = pegi;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public LocalDate getRealaseDate() {
        return realaseDate;
    }

    public void setRealaseDate(LocalDate realaseDate) {
        this.realaseDate = realaseDate;
    }

    public String getPegi() {
        return pegi;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Videogames that = (Videogames) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "id: " + id + '\n' +
                "Nombre: " + name + '\n' +
                "Almacenamiento: " + storage + "MB" + '\n' +
                "Fecha de salida: " + realaseDate + '\n' +
                "Edad minima (Pegi): " + pegi + '\n' +
                "Precio: " + price + "€";
    }
}
