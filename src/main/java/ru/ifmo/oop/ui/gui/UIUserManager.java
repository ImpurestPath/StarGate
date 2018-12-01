package ru.ifmo.oop.ui.gui;

import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.domain.UserManager;

public class UIUserManager {
    private final UserManager userManager;
    private final int idGatePlanet;
    private User currentUser;
    private UserMode mode;
    public UIUserManager(int idGatePlanet, UserManager userManager) {
        this.idGatePlanet = idGatePlanet;
        this.userManager = userManager;
        this.mode = UserMode.USER;
    }

    public User get(String name) throws ExceptionDAO {
        return userManager.get(name);
    }

    private UserMode getMode(String permissions) {
        return permissions.contains("a") ? UserMode.ADMIN : UserMode.USER;
    }

    public void add(User user) throws ExceptionDAO {
        userManager.add(user);
        this.currentUser = user;
        this.mode = getMode(user.getPermission());
    }

    public void update(User user) throws ExceptionDAO {
        userManager.update(user);
        this.currentUser = userManager.get(user.getName());
        this.mode = getMode(user.getPermission());
    }

    public void delete(int idUser) throws ExceptionDAO {
        userManager.delete(idUser);
    }

    public UserMode getMode() {
        return mode;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        this.mode = getMode(currentUser.getPermission());
    }

    public int getIdGatePlanet() {
        return idGatePlanet;
    }

    public enum UserMode {
        ADMIN,
        USER
    }
}
