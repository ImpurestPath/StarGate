package Terminal;

public class User implements Searchable {
    private final String name;
    public Planet currentPlanet;

    public User(String name, Planet planet) {
        this.name = name;
        this.currentPlanet = planet;
    }

    public String getName(){
        return this.name;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        return false;
    }

}
