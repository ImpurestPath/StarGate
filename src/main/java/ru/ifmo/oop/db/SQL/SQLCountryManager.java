package ru.ifmo.oop.db.SQL;

import ru.ifmo.oop.db.CountryDAO;
import ru.ifmo.oop.db.DTO.CountryDB;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCountryManager implements CountryDAO {
    private Connection connection;

    public SQLCountryManager(SQLConnection sqlConnection) {
        this.connection = sqlConnection.getConnection();
    }

    public int insert( int idPlanet, CountryDB country) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementCountry = connection.prepareStatement("INSERT INTO" +
                    " Country('idPlanet','name','area') VALUES (?,?,?)");
            preparedStatementCountry.setInt(1, idPlanet);
            preparedStatementCountry.setString(2, country.getName());
            preparedStatementCountry.setLong(3, country.getArea());
            preparedStatementCountry.execute();
            int countryID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            country.setId(countryID);
            return countryID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
    public void delete(int idCountry) throws ExceptionDAO {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Country WHERE idCountry = ?");
            preparedStatement.setInt(1,idCountry);
            preparedStatement.execute();
        }
        catch (SQLException e){
            throw new ExceptionDAO(e);
        }
    }
    public void update(int idCountry, CountryDB country) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Country SET name = ?, area = ? WHERE idCountry = ?");
            preparedStatement.setString(1,country.getName());
            preparedStatement.setLong(2,country.getArea());
            preparedStatement.setInt(3,idCountry);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
    @Override
    public List<CountryDB> getPlanetCountries(int id) throws ExceptionDAO {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT idCountry,name,area FROM Country WHERE idPlanet = ?")) {
            List<CountryDB> countries = new ArrayList<>();
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                countries.add(new CountryDB(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getLong(3)));
            }
            return countries;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
