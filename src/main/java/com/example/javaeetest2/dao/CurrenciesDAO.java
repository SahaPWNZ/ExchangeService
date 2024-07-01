package com.example.javaeetest2.dao;

import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.CurrencyResponseDTO;
import com.example.javaeetest2.exceptions.DatabaseException;
import com.example.javaeetest2.exceptions.EntityExistException;
import com.example.javaeetest2.utils.ConnectionManager;
import org.sqlite.SQLiteErrorCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class CurrenciesDAO {
    private static final String SELECT_ALL_CURRENCIES = "SELECT * FROM Currencies";
    private static final String INSERT_CURRENCY = "INSERT INTO Currencies   (Code, FullName, Sign) VALUES (?,?,?)";
    private static final String SELECT_CURRENCY_ON_CODE = "SELECT * FROM Currencies WHERE Code = ?";
    private static final String SELECT_CURRENCY_ON_ID = "SELECT * FROM Currencies WHERE Id = ?";

    private CurrencyResponseDTO getDtoByResultSet(ResultSet resultSet) throws SQLException {
        return new CurrencyResponseDTO(
                resultSet.getInt("id"),
                resultSet.getString("Code"),
                resultSet.getString("FullName"),
                resultSet.getString("Sign"));
    }

    public Optional<CurrencyResponseDTO> findByCode(String code) throws DatabaseException {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_CODE);
        ) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getDtoByResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }

    }

    public ArrayList<CurrencyResponseDTO> findAll() throws DatabaseException {
        ArrayList<CurrencyResponseDTO> AllCurrencies = new ArrayList<>();
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_CURRENCIES);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AllCurrencies.add(getDtoByResultSet(resultSet));
            }
            return AllCurrencies;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public Optional<CurrencyResponseDTO> save(CurrencyRequestDTO curDTO) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_CURRENCY)) {
            preparedStatement.setString(1, curDTO.getCode());
            preparedStatement.setString(2, curDTO.getFullName());
            preparedStatement.setString(3, curDTO.getSign());
            preparedStatement.executeUpdate();
            System.out.println("Валюта добавлена");
            return findByCode(curDTO.getCode());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (e.getErrorCode() == SQLiteErrorCode.SQLITE_CONSTRAINT.code){
                throw new EntityExistException("Валюта с заданным кодом уже есть в БД");
            }
            else {
                throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
            }}
    }


    public Optional<CurrencyResponseDTO> findById(int id) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getDtoByResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public int findIdByCode(String code) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENCY_ON_CODE);
        ) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Ошибка при обработке запроса или при подключении к БД");
        }
    }
}
