package com.example.test;

public class User{
    private int id_User;
    private String User;
    private String Password;

    public User(int id_User, String user, String password) {
        this.id_User = id_User;
        User = user;
        Password = password;
    }

    public int getId_User() {
        return id_User;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }
}
