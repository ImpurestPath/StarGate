package ru.ifmo.oop.dataAccess.DTO;
public class UserDTO {
    private int id;
    private final String name;
    private final int idCurrentPlanet;
    private final String permissions;
    private final String password;
    public UserDTO(String name, String permissions, int idCurrentPlanet, String password){
        this.name = name;
        this.permissions = permissions;
        this.idCurrentPlanet = idCurrentPlanet;
        this.password =  password;
    }
    public UserDTO(int id, String name, String permissions, int idCurrentPlanet, String password){
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idCurrentPlanet;
        this.permissions = permissions;
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    public int getIdCurrentPlanet(){
        return this.idCurrentPlanet;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPermissions() {
        return permissions;
    }
    public String getPassword() {
        return password;
    }
}
