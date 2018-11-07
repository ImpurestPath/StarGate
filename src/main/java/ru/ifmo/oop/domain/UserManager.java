package ru.ifmo.oop.domain;


import ru.ifmo.oop.db.Exception.ExceptionDAO;
import ru.ifmo.oop.db.SQL.SQLConnection;
import ru.ifmo.oop.db.SQL.SQLUserManager;
import ru.ifmo.oop.db.UserDAO;
import ru.ifmo.oop.domain.Mappers.TransformerToDTO;
import ru.ifmo.oop.domain.Mappers.TransformerToEntity;

public class UserManager {

    private final UserDAO userDAO;
    private final SQLConnection connection;

    public UserManager(String file) throws ExceptionDAO {
        this.connection = SQLConnection.getInstance(file);
        this.userDAO = new SQLUserManager(connection);
    }

    public User get(String name) throws ExceptionDAO {
        return TransformerToEntity.toUser(userDAO.get(name));
    }

    public void update(int idUser, User user) throws ExceptionDAO {
        userDAO.update(idUser, TransformerToDTO.toUser(user));
        connection.commit();
    }
    public void add(User user) throws ExceptionDAO {
        user.setId(userDAO.insert(TransformerToDTO.toUser(user)));
        connection.commit();
    }
    public void delete(int idUser) throws ExceptionDAO {
        userDAO.delete(idUser);
        connection.commit();
    }
}
