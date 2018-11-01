package db;

import java.sql.*;
import java.util.*;
import java.util.List;

public class SQLPlanetManager implements PlanetDAO {
    private Connection connection;

    public SQLPlanetManager(SQLConnection sqlConnection){
        this.connection = sqlConnection.connection;
    }

    public List<PlanetDB> getAll() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlanetDB> planets = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT idPlanet, name, temperature, pressure FROM Planet");
            while (resultSet.next()) {
                planets.add(new PlanetDB(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getLong(4)));
            }
            return planets;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int add(PlanetDB planet) throws ExceptionDAO {
        try (Statement statement = this.connection.createStatement()) {
            PreparedStatement preparedStatementPlanet = this.connection.prepareStatement(
                    "INSERT INTO Planet(`name`, `temperature`, `pressure`) " +
                            "VALUES(?, ?, ?)");
            preparedStatementPlanet.setString(1, planet.getName());
            preparedStatementPlanet.setInt(2, planet.getTemperature());
            preparedStatementPlanet.setLong(3, planet.getPressure());
            preparedStatementPlanet.execute();
            ResultSet lastId = statement.executeQuery("SELECT last_insert_rowid()");
            int id = lastId.getInt(1);
            planet.setId(id);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }

    /*private void add(int id, PlanetDB planet) throws ExceptionDAO {
        try (PreparedStatement preparedStatementPlanet = this.connection.prepareStatement(
                "INSERT INTO Planet('idPlanet',`name`, `temperature`, `pressure`) " +
                        "VALUES(?,?, ?, ?)")) {
            preparedStatementPlanet.setInt(1, id);
            preparedStatementPlanet.setString(2, planet.getName());
            preparedStatementPlanet.setInt(3, planet.getTemperature());
            preparedStatementPlanet.setLong(4, planet.getPressure());
            preparedStatementPlanet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }*/

    public void delete(int id) throws ExceptionDAO {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Planet WHERE idPlanet = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }

    public void update(int idPlanet, PlanetDB planet) throws ExceptionDAO {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Planet " +
                             "SET name = ?, temperature = ?, pressure = ? " +
                             "WHERE idPlanet = ?")){
            preparedStatement.setString(1, planet.getName());
            preparedStatement.setInt(2, planet.getTemperature());
            preparedStatement.setLong(3, planet.getPressure());
            preparedStatement.setInt(4, idPlanet);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }


}
