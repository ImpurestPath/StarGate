package ru.ifmo.oop.dataAccess.SQL;

import ru.ifmo.oop.dataAccess.CountryDAO;
import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCountryDAO implements CountryDAO {
    private Connection connection;

    SQLCountryDAO(Connection connection) {
        this.connection = connection;
    }

    public int add(int idPlanet, CountryDTO country) throws ExceptionDAO {
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

    @Override
    public CountryDTO get(int id) {
        return null; //Not implemented
    }

    @Override
    public int add(CountryDTO obj) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementCountry = connection.prepareStatement("INSERT INTO" +
                    " Country('idPlanet','name','area') VALUES (?,?,?)");
            preparedStatementCountry.setInt(1, obj.getIdPlanet());
            preparedStatementCountry.setString(2, obj.getName());
            preparedStatementCountry.setLong(3, obj.getArea());
            preparedStatementCountry.execute();
            int countryID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            obj.setId(countryID);
            return countryID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    public void update(CountryDTO country) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Country SET name = ?, area = ? WHERE idCountry = ?");
            preparedStatement.setString(1,country.getName());
            preparedStatement.setLong(2,country.getArea());
            preparedStatement.setInt(3,country.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
    @Override
    public List<CountryDTO> getPlanetCountries(int idPlanet) throws ExceptionDAO {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT idCountry,name,area FROM Country WHERE idPlanet = ?")) {
            List<CountryDTO> countries = new ArrayList<>();
            preparedStatement.setInt(1,idPlanet);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                countries.add(new CountryDTO(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        idPlanet));
            }
            return countries;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
