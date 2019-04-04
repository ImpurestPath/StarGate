package ru.ifmo.oop.ui.gui;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.domain.interfaces.IUserManager;
import ru.ifmo.oop.domain.User;

public class UIUserManager {
    private final IUserManager userManager;
    private final int idGatePlanet;
    private User currentUser;
    private UserMode mode;
    public UIUserManager(int idGatePlanet, IUserManager userManager) {
        this.idGatePlanet = idGatePlanet;
        this.userManager = userManager;
        this.mode = UserMode.USER;
    }

    public User get(String name) throws DatabaseError {
        return userManager.get(name);
    }

    private UserMode getMode(String permissions) {
        return permissions.contains("a") ? UserMode.ADMIN : UserMode.USER;
    }

    public void add(User user) throws DatabaseError {
        userManager.add(user);
        this.currentUser = user;
        this.mode = getMode(user.getPermission());
    }

    public void update(User user) throws DatabaseError {
        userManager.update(user);
        this.currentUser = userManager.get(user.getName());
        this.mode = getMode(user.getPermission());
    }

    public void delete(User user) throws DatabaseError {
        userManager.delete(user);
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
