package com.example.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {

        try {

            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "admin012", "O!#osTR2RIsPoGyr(d");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }
}
