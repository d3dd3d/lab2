package com.d3d.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPostgreSQL {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/calc";
    static final String USER = "postgres";
    static final String PASSWORD = "Samara01";

    public static void main(String[] args){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("Не удалось найти драйвер");
            return;
        }
        Connection connection = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
        catch(SQLException e){
            System.out.println("Не удалось подключится");
        }

        if(connection!=null){
            System.out.println("Удалось подключится");
        }
        else{
            System.out.println("Не удалось подключится 2");
        }
    }
}
