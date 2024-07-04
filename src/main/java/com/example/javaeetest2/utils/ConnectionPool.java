package com.example.javaeetest2.utils;

import com.example.javaeetest2.exceptions.DatabaseException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final HikariConfig HIKARI_CONFIG = new HikariConfig();
    private static final HikariDataSource HIKARI_DATA_SOURCE;

    static {
        HIKARI_CONFIG.setDriverClassName("org.sqlite.JDBC");
        HIKARI_CONFIG.setJdbcUrl("jdbc:sqlite::resource:new.db");
        HIKARI_DATA_SOURCE = new HikariDataSource(HIKARI_CONFIG);
    }

    public static Connection getConnection() {
        try {
            return HIKARI_DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при подключении к БД");
        }
    }
}

