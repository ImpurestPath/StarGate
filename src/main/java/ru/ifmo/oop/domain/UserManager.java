package ru.ifmo.oop.domain;


import ru.ifmo.oop.db.Exception.ExceptionDAO;
import ru.ifmo.oop.db.SQL.SQLConnection;
import ru.ifmo.oop.db.SQL.SQLUserDAO;
import ru.ifmo.oop.db.UserDAO;
import ru.ifmo.oop.domain.Mappers.TransformerToDTO;
import ru.ifmo.oop.domain.Mappers.TransformerToEntity;

public class UserManager {

    private final SQLConnection connection;

    public UserManager(String file) throws ExceptionDAO {
        this.connection = SQLConnection.getInstance(file);
    }

    public User get(String name) throws ExceptionDAO {
        return TransformerToEntity.toUser(connection.getUser(name));
    }

    public void update(int idUser, User user) throws ExceptionDAO {
        connection.updateUser(idUser, TransformerToDTO.toUser(user));
        connection.commit();
    }
    public void add(User user) throws ExceptionDAO {
        user.setId(connection.addUser(TransformerToDTO.toUser(user)));
        connection.commit();
    }
    public void delete(int idUser) throws ExceptionDAO {
        connection.deleteUser(idUser);
        connection.commit();
    }
}
