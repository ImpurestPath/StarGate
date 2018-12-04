package ru.ifmo.oop.dataAccess.SQL;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.dataAccess.UserDAO;
import ru.ifmo.oop.dataAccess.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLUserDAO implements UserDAO {
    private Connection connection;

    SQLUserDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public UserDTO get(String name) throws DatabaseError {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idUser,Name,Permission,idCurrentPlanet,Password FROM User WHERE Name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            if (resultSet.next())
            return new UserDTO(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5));
            else return null;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Override
    public int add(UserDTO user) throws DatabaseError {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO" +
                    " User('Name','idCurrentPlanet','Permission','Password') VALUES (?,?,?,?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getIdCurrentPlanet());
            preparedStatement.setString(3,user.getPermissions());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.execute();
            int raceID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            user.setId(raceID);
            return raceID;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Override
    public void delete(int idUser) throws DatabaseError {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE idUser = ?");
            preparedStatement.setInt(1,idUser);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new DatabaseError(e);
        }

    }

    @Override
    public UserDTO get(int id) throws DatabaseError {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idUser,Name,Permission,idCurrentPlanet,Password FROM User WHERE idUser = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            if (resultSet.next())
                return new UserDTO(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5));
            else return null;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Override
    public void update(UserDTO user) throws DatabaseError {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE User SET name = ?, idCurrentPlanet = ?,Permission = ?, Password = ? WHERE idUser = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getIdCurrentPlanet());
            preparedStatement.setString(3,user.getPermissions());
            preparedStatement.setInt(5,user.getId());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }
}
