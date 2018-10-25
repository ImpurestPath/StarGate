package DB;

public class UserDB {
    private int id;
    private String name;
    private int idCurrentPlanet;
    UserDB(String name, int idCurrentPlanet){
        this.name = name;
        this.idCurrentPlanet = idCurrentPlanet;
    }

    public String getName() {
        return this.name;
    }
    public int getIdCurrentPlanet(){
        return this.idCurrentPlanet;
    }
}
