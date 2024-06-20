package com.example.javaeetest2.DAO;

import com.example.javaeetest2.DTO.ExchangeRateRequestDTO;
import com.example.javaeetest2.DTO.ExchangeRateResponseDTO;
import com.example.javaeetest2.Exceptions.CastomSQLException;
import com.example.javaeetest2.Exceptions.ConflictException;
import com.example.javaeetest2.Exceptions.InvalidDataException;
import com.example.javaeetest2.Utils.ConnectionManager;
import org.sqlite.SQLiteErrorCode;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ExchangeRatesDAO {
    private static final String SELECT_ALL_EXCHANGE_RATES = "SELECT * FROM ExchangeRates";
    private static final String SELECT_EXCHANGE_RATE_ON_CODES = "SELECT * FROM ExchangeRates " +
            "WHERE BaseCurrencyid = (SELECT id FROM Currencies WHERE Code = ?) " +
            "AND TargetCurrencyid = (SELECT id FROM Currencies WHERE Code = ?)";
    private static final String INSERT_EXCHANGE_RATE = "INSERT INTO ExchangeRates   (Rate, BaseCurrencyId, TargetCurrencyId) VALUES (?,?,?)";

    private static final String UPDATE_EXCHANGE_RATE = "UPDATE ExchangeRates SET Rate = ? " +
            "WHERE BaseCurrencyId = (SELECT id FROM Currencies WHERE Code = ?) " +
            "AND TargetCurrencyId = (SELECT id FROM Currencies WHERE Code = ?)";

    private static final String GET_RATE_BY_CODE = "SELECT * FROM ExchangeRates " +
            "WHERE BaseCurrencyid = (SELECT id FROM Currencies WHERE Code = ?)" +
            "AND TargetCurrencyid = (SELECT id FROM Currencies WHERE Code = ?) ";
    private static final CurrenciesDAO curDAO = new CurrenciesDAO();

    private ExchangeRateResponseDTO getDtoByResulSet(ResultSet resultSet) throws SQLException {
        return new ExchangeRateResponseDTO(resultSet.getInt("id"),
                ExchangeRatesDAO.curDAO.getCurrencyOnId(resultSet.getInt("BaseCurrencyId")).orElseThrow(() -> new InvalidDataException("Одной с валют нет в БД")),
                ExchangeRatesDAO.curDAO.getCurrencyOnId(resultSet.getInt("TargetCurrencyId")).orElseThrow(() -> new InvalidDataException("Одной с валют нет в БД")),
                resultSet.getBigDecimal("Rate"));
    }

    public ArrayList<ExchangeRateResponseDTO> getAllExchangeRates() {
        ArrayList<ExchangeRateResponseDTO> allExchangeRates = new ArrayList<>();
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_EXCHANGE_RATES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allExchangeRates.add(getDtoByResulSet(resultSet));
            }
            return allExchangeRates;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public Optional<ExchangeRateResponseDTO> getExchangeRateOnCodes(String baseCurrencyCode, String targetCurrencyCode) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_EXCHANGE_RATE_ON_CODES)) {
            preparedStatement.setString(1, baseCurrencyCode);
            preparedStatement.setString(2, targetCurrencyCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getDtoByResulSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

    public Optional<ExchangeRateResponseDTO> insertExchangeRate(ExchangeRateRequestDTO exchangeRateRequestDTO) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_EXCHANGE_RATE)) {

            preparedStatement.setBigDecimal(1, exchangeRateRequestDTO.getRate());
            preparedStatement.setInt(2, curDAO.getCurrencyIdByCode(exchangeRateRequestDTO.getBaseCode()));
            preparedStatement.setInt(3, curDAO.getCurrencyIdByCode(exchangeRateRequestDTO.getTargetCode()));
            preparedStatement.executeUpdate();
            return getExchangeRateOnCodes(exchangeRateRequestDTO.getBaseCode(),
                    exchangeRateRequestDTO.getTargetCode());
        } catch (SQLException e) {
            if (e.getErrorCode() == SQLiteErrorCode.SQLITE_CONSTRAINT.code){
               throw new ConflictException("Введённый курс валют уже есть в БД!");
            }
            else {
                System.out.println(e.getMessage());
                throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
            }
        }
    }

    public ExchangeRateResponseDTO updateRate(ExchangeRateRequestDTO exchangeRateRequestDTO) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_EXCHANGE_RATE)) {
            preparedStatement.setBigDecimal(1, exchangeRateRequestDTO.getRate());
            preparedStatement.setString(2, exchangeRateRequestDTO.getBaseCode());
            preparedStatement.setString(3, exchangeRateRequestDTO.getTargetCode());
            preparedStatement.executeUpdate();
            return (getExchangeRateOnCodes(exchangeRateRequestDTO.getBaseCode(),
                    exchangeRateRequestDTO.getTargetCode())).get();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
        }
    }


    public BigDecimal getRateOnCodes(String baseCurrencyCode, String targetCurrencyCode) {
        try (var conn = ConnectionManager.open();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_RATE_BY_CODE)) {
            preparedStatement.setString(1, baseCurrencyCode);
            preparedStatement.setString(2, targetCurrencyCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getBigDecimal("rate");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CastomSQLException("Ошибка при обработке запроса или при подключении к БД");
        }
    }

}
