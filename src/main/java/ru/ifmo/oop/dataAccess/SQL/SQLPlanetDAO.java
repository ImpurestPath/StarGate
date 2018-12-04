package ru.ifmo.oop.dataAccess.SQL;

import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.dataAccess.PlanetDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SQLPlanetDAO implements PlanetDAO {
    private Connection connection;

    SQLPlanetDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PlanetDTO> getAll() {
        try (Statement statement = this.connection.createStatement()) {
            List<PlanetDTO> planets = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT idPlanet, name, temperature, pressure FROM Planet");
            connection.commit();
            while (resultSet.next()) {
                planets.add(new PlanetDTO(
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

    @Override
    public PlanetDTO get(int idPlanet) throws DatabaseError {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idPlanet, name, temperature, pressure FROM Planet " +
                                     "WHERE idPlanet = ?")) {
            preparedStatement.setInt(1, idPlanet);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                int temperature = resultSet.getInt(3);
                long pressure = resultSet.getLong(4);
                return new PlanetDTO(idPlanet, name, temperature, pressure);
            }
            else return null;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public int add(PlanetDTO planet) throws DatabaseError {
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
            throw new DatabaseError(e);
        }
    }

    public void delete(int idPlanet) throws DatabaseError {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Planet WHERE idPlanet = ?")) {
            statement.setObject(1, idPlanet);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseError(e);
        }
    }

    public void update(PlanetDTO planet) throws DatabaseError {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Planet " +
                             "SET name = ?, temperature = ?, pressure = ? " +
                             "WHERE idPlanet = ?")) {
            preparedStatement.setString(1, planet.getName());
            preparedStatement.setInt(2, planet.getTemperature());
            preparedStatement.setLong(3, planet.getPressure());
            preparedStatement.setInt(4, planet.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }


}
