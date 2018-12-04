package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.exception.ConnectionError;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.dataAccess.SQL.SQLConnection;
import ru.ifmo.oop.domain.mappers.TransformerToDTO;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;

public class UserManager {

    private final SQLConnection connection;

    public UserManager(String file) throws ConnectionError {
        this.connection = SQLConnection.getInstance(file);
    }

    public User get(String name) throws DatabaseError {
        return TransformerToEntity.toUser(connection.getUser(name));
    }

    public void update(User user) throws DatabaseError {
        connection.updateUser(TransformerToDTO.toUser(user));
        connection.commit();
    }
    public void add(User user) throws DatabaseError {
        user.setId(connection.addUser(TransformerToDTO.toUser(user)));
        connection.commit();
    }
    public void delete(int idUser) throws DatabaseError {
        connection.deleteUser(idUser);
        connection.commit();
    }
}
