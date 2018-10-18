package Terminal;

public class User implements WithName {
    private String name;
    public Planet planet;

    public User(String name, Planet planet) {
        this.name = name;
        this.planet = planet;
    }
    public String getName(){ return name;}
    public Planet getPlanet() {
        return planet;
    }

}
