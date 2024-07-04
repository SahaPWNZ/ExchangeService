package com.example.javaeetest2.dao;

import com.example.javaeetest2.exceptions.DatabaseException;
import com.example.javaeetest2.exceptions.EntityExistException;
import com.example.javaeetest2.utils.ConnectionPool;
import org.sqlite.SQLiteErrorCode;
import com.example.javaeetest2.model.Currency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class CurrenciesDAO implements CrudDAO<Currency> {
    private static final String SELECT_ALL_CURRENCIES = "SELECT * FROM Currencies";
    private static final String INSERT_CURRENCY = "INSERT INTO Currencies   (Code, FullName, Sign) VALUES (?,?,?)";
    private static final String SELECT_CURRENCY_ON_CODE = "SELECT * FROM Currencies WHERE Code = ?";
    private static final String SELECT_CURRENCY_ON_ID = "SELECT * FROM Currencies WHERE Id = ?";

    private Currency getCurrency(ResultSet resultSet) throws SQLException {
        return new com.example.javaeetest2.model.Currency(
                resultSet.getLong("id"),
                resultSet.getString("Code"),
                resultSet.getString("FullName"),
                resultSet.getString("Sign"));
    }

    public Optional<Currency> findByCode(String code) throws DatabaseException {
        try (var conn = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_CODE))
        {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(getCurrency(resultSet));
            } else {
                return Optional.empty();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }

    }

    public ArrayList<Currency> findAll() throws DatabaseException {
        ArrayList<Currency> AllCurrencies = new ArrayList<>();
        try (var conn = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_CURRENCIES))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AllCurrencies.add(getCurrency(resultSet));
            }
            return AllCurrencies;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public Optional<Currency> save(Currency entity) {
        try (var conn = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_CURRENCY))
        {
            preparedStatement.setString(1, entity.getCode());
            preparedStatement.setString(2, entity.getFullName());
            preparedStatement.setString(3, entity.getSign());
            preparedStatement.executeUpdate();
            return findByCode(entity.getCode());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            if (e.getErrorCode() == SQLiteErrorCode.SQLITE_CONSTRAINT.code) {
                throw new EntityExistException("Валюта с заданным кодом уже есть в БД");
            } else {
                throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
            }
        }
    }


    public Optional<Currency> findById(Long id) {
        try (var conn = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_ID))
        {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(getCurrency(resultSet));
            } else {
                return Optional.empty();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public int findIdByCode(String code) {
        try (var conn = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_CODE))
        {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("id");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }
}
