package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.Dao.DaoUser;
import com.example.proyectojavafx.Dao.impl.DaoUserImplement;
import com.example.proyectojavafx.Models.User;

public class UserServices {
    DaoUser dI = new DaoUserImplement();

    public void adder(String name, String password){
        dI.insertUser(new User(name,password));
    }
}
