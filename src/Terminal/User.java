package Terminal;

public class User {
    private String name;
    public Planet planet;

    public User(String name, Planet planet) {
        this.name = name;
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

}
