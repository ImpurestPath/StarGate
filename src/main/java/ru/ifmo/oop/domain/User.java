package ru.ifmo.oop.domain;

import ru.ifmo.oop.domain.interfaces.Comparable;

public class User implements Comparable {
    private int id;
    private final String name;
    private final String permission;
    private final String password;
    int idCurrentPlanet;

    public User(String name, String permission, int  idPlanet, String password) {
        this.id = -1;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
        this.permission = permission;
        this.password = password;
    }
    public User(int id,String name, String permission, int  idPlanet, String password) {
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
        this.permission = permission;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public int getIdCurrentPlanet() {
        return this.idCurrentPlanet;
    }

    @Override
    public <T> boolean compare(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        else if (id.getClass() == Integer.class){
            return Integer.toString(this.id).equals(id.toString());
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public String getPassword() {
        return password;
    }
}
