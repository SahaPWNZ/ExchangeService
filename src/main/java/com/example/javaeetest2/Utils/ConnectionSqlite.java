package com.example.javaeetest2.Utils;

import java.sql.*;

//public class ConnectionSqlite {
//    private static Connection connection;
//
//    public static Connection getConnection() {
//        try {
//            if (connection == null) {
//                Class.forName("org.sqlite.JDBC");
//                connection = DriverManager.getConnection("jdbc:sqlite::resource:new.db");
//            }
//            System.out.println("Connection to SQLite has been.");
//            return connection;
//        } catch (Exception e) {
//            System.out.println("Ошибка при подключении к базе");
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//}


//    void connection() {
//        try (conn = DriverManager.getConnection()){
//            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite::resource:new.db";
//            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been.");
//            statement = conn.createStatement();
//        } catch (Exception e) {
//            System.out.println("Ошибка при подключении к базе");
//            System.out.println(e.getMessage());
//        }
//    }
//    public void getAllCurrency () {
//        try {
//            resultSet = statement.executeQuery
//                    ("select * from Currencies where id = 1");
//            while (resultSet.next()) {
//                System.out.print(resultSet.getString(1) + " | ");
//                System.out.print(resultSet.getString(2) + " | ");
//                System.out.print(resultSet.getString(3) + " | ");
//                System.out.print(resultSet.getString(4) + "\n");
//            }
//
//        }
//        catch (Exception e){
//            System.out.println("Ошибка при выполнении запроса");
//            System.out.println(e.getMessage());
//    }
//    }



