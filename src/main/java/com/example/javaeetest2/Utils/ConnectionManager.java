package com.example.javaeetest2.Utils;

import com.example.javaeetest2.Exceptions.CastomSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static {
        loadDriver();
    }
    private ConnectionManager() {
    }
    public static Connection open() {
        try {

            return DriverManager.getConnection("jdbc:sqlite::resource:new.db"); //C:\Users\User\IdeaProjects\JavaEETest2\src\main\resources\new(ломаная таблица).db
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
        }
    }
    static void loadDriver() {
        try {
//            System.out.println(ConnectionManager.class.getClassLoader().getResource("new.db"));
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
