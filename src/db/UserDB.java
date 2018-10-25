package db;

public class UserDB {
    private int id;
    private String name;
    private int idCurrentPlanet;
    UserDB(String name, int idCurrentPlanet){
        this.name = name;
        this.idCurrentPlanet = idCurrentPlanet;
    }
    UserDB(int id, String name, int idCurrentPlanet){
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idCurrentPlanet;
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
}
