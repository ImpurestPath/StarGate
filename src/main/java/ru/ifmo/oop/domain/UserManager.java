package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLUserDAO;
import ru.ifmo.oop.dataAccess.UserDAO;
import ru.ifmo.oop.dataAccess.exception.ConnectionError;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.mappers.TransformerToDTO;
import ru.ifmo.oop.mappers.TransformerToEntity;

import java.sql.Connection;

public class UserManager implements Manager<User> {
    private final ConnectionDAO connection;
    private final UserDAO userDAO;

    public UserManager(ConnectionDAO<Connection> connection) throws ConnectionError {
        this.connection = connection;
        this.userDAO = new SQLUserDAO(connection.getConnection());
    }

    public User get(String name) throws DatabaseError {
        return TransformerToEntity.toUser(userDAO.get(name));
    }

    public void update(User user) throws DatabaseError {
        userDAO.update(TransformerToDTO.toUser(user));
        connection.commit();
    }

    @Override
    public User get(int id) throws DatabaseError {
        return null; //No
    }

    public void add(User user) throws DatabaseError {
        user.setId(userDAO.add(TransformerToDTO.toUser(user)));
        connection.commit();
    }

    @Override
    public void delete(User obj) throws DatabaseError {
        userDAO.delete(obj.getId());
        connection.commit();
    }

    public void delete(int idUser) throws DatabaseError {
        userDAO.delete(idUser);
        connection.commit();
    }
}
