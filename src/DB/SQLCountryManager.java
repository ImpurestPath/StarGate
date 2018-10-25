package DB;

import Terminal.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCountryManager implements CountryDAO {
    private Connection connection;

    public SQLCountryManager(SQLConnection sqlConnection) throws ExceptionDAO {
        this.connection = sqlConnection.connection;
    }

    public int insert(CountryDB country, int idPlanet) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementCountry = connection.prepareStatement("INSERT INTO" +
                    " Country('idPlanet','name','area') " + "VALUES (?,?,?)");
            preparedStatementCountry.setInt(1, idPlanet);
            preparedStatementCountry.setString(2, country.getName());
            preparedStatementCountry.setLong(3, country.getArea());
            preparedStatementCountry.execute();
            int countryID = this.connection.createStatement().executeQuery("SELECT last_insert_rowid()").getInt(1);
            country.setId(countryID);
            return countryID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
            /*for (RaceDB race :
                    country.getRaces()) {
                PreparedStatement preparedStatementRace = connection.prepareStatement("INSERT INTO" +
                        " Race('idCountry','name','amount','idBehavior') " + "VALUES (?,?,?,?)");
                preparedStatementRace.setInt(1, countryID);
                preparedStatementRace.setString(2, race.getName());
                preparedStatementRace.setLong(3, race.getAmount());
                preparedStatementRace.setInt(4, race.getBoolBehavior() ? 2 : 1);
                preparedStatementRace.execute();
            }*/
    }
    public void delete(int idCountry) throws ExceptionDAO {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Country WHERE idCountry = ?");
            preparedStatement.setInt(1,idCountry);
            preparedStatement.execute();
        }
        catch (SQLException e){
            throw new ExceptionDAO(e);
        }
    }
    public void update(int idCountry, CountryDB country) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Country SET name = ?, area = ?");
            preparedStatement.setString(1,country.getName());
            preparedStatement.setLong(2,country.getArea());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
    @Override
    public List<CountryDB> getPlanetCountries(int id) throws ExceptionDAO {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT idCountry,name,area FROM Country")) {
            List<CountryDB> countries = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countries.add(new CountryDB(resultSet.getInt(1), resultSet.getString(2), resultSet.getLong(3)));
            }
            return countries;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
