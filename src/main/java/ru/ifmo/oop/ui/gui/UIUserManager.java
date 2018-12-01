package ru.ifmo.oop.ui.gui;

import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.domain.UserManager;

public class UIUserManager {
    public enum UserMode {
        ADMIN,
        USER
    }

    private final UserManager userManager;
    private User user;
    private final int idGatePlanet;
    private UserMode mode;
    private static UIUserManager instance = null;

/*    public static synchronized UIUserManager getInstance() {
        //if (instance == null) throw new Exception("No controller");
        //else
        return instance;
    }*/

    public UIUserManager(int idGatePlanet, UserManager userManager) {
        this.idGatePlanet = idGatePlanet;
        this.userManager = userManager;
        instance = this;
        this.mode = UserMode.USER;
    }

    public User getUser(String name) throws ExceptionDAO {
        return userManager.get(name);
    }

    private UserMode getMode(String permissions) {
        return permissions.contains("a") ? UserMode.ADMIN : UserMode.USER;
    }

    public void setUser(User user) {
        this.user = user;
        this.mode = getMode(user.getPermission());
    }

    public void addUser(User user) throws ExceptionDAO {
        userManager.add(user);
        this.user = user;
        this.mode = getMode(user.getPermission());
    }

    public void changeUser(int idUser, User user) throws ExceptionDAO {
        userManager.update(idUser, user);
        this.user = userManager.get(user.getName());
        this.mode = getMode(user.getPermission());
    }

    public void deleteUser(int idUser) throws ExceptionDAO {
        userManager.delete(idUser);
    }

    public UserMode getMode() {
        return mode;
    }

    public int getIdGatePlanet() {
        return idGatePlanet;
    }
}
