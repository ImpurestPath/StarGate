package terminal;

import db.*;

public class UserManager {
    public static void moveUser(Planet planet, User user) {
        if (planet != null)
            user.idCurrentPlanet = planet.getId();
    }

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
