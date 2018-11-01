package db;

public class UserDB {
    private int id;
    private final String name;
    private final int idCurrentPlanet;
    private final String permissions;
    UserDB(String name,String permissions, int idCurrentPlanet){
        this.name = name;
        this.permissions = permissions;
        this.idCurrentPlanet = idCurrentPlanet;
    }
    UserDB(int id, String name, String permissions, int idCurrentPlanet){
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idCurrentPlanet;
        this.permissions = permissions;
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
}
