package com.example.proyectojavafx.Services;

import com.example.proyectojavafx.Dao.DaoUser;
import com.example.proyectojavafx.Dao.impl.DaoUserImplement;
import com.example.proyectojavafx.Models.User;

public class UserServices {
    DaoUser dI = new DaoUserImplement();

    public boolean adder(String name, String password){
        return dI.insertUser(new User(name,password));
    }
}
