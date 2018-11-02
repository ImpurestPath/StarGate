package terminal;

import db.*;

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
