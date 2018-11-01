package terminal;

import db.*;

public class UserManager {

    private UserDAO userDAO;

    public UserManager(String file) throws ExceptionDAO {
        SQLConnection connection = SQLConnection.getInstance(file);
        userDAO = new SQLUserManager(connection);
    }

    public User get(String name) throws ExceptionDAO {
        return TransformerToEntity.toUser(userDAO.get(name));
    }

    public void update(int idUser, User user) throws ExceptionDAO {
        userDAO.update(idUser, TransformerToDTO.toUser(user));
    }
    public void add(User user) throws ExceptionDAO {
        user.setId(userDAO.insert(TransformerToDTO.toUser(user)));
    }
    public void delete(int idUser) throws ExceptionDAO {
        userDAO.delete(idUser);
    }
}
