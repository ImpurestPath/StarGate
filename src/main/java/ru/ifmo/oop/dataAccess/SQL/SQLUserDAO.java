package ru.ifmo.oop.dataAccess.SQL;

import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.dataAccess.UserDAO;
import ru.ifmo.oop.dataAccess.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLUserDAO implements UserDAO {
    private Connection connection;

    public SQLUserDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public UserDTO get(String name) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idUser,Name,Permission,idCurrentPlanet FROM User WHERE Name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            if (resultSet.next())
            return new UserDTO(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
            else return null;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public int insert(UserDTO user) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementRace = connection.prepareStatement("INSERT INTO" +
                    " User('Name','idCurrentPlanet','Permission') VALUES (?,?,?)");

            preparedStatementRace.setString(1, user.getName());
            preparedStatementRace.setInt(2, user.getIdCurrentPlanet());
            preparedStatementRace.setString(3,user.getPermissions());
            preparedStatementRace.execute();
            int raceID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            user.setId(raceID);
            return raceID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public void delete(int idUser) throws ExceptionDAO {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE idUser = ?");
            preparedStatement.setInt(1,idUser);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }

    }

    @Override
    public void update(int idUser, UserDTO user) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE User SET name = ?, idCurrentPlanet = ?,Permission = ? WHERE idUser = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getIdCurrentPlanet());
            preparedStatement.setString(3,user.getPermissions());
            preparedStatement.setInt(4,idUser);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
