package terminal;

public class User implements Searchable {
    private int id;
    private final String name;
    public int idCurrentPlanet;

    public User(String name, int  idPlanet) {
        this.id = -1;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
    }
    public User(int id,String name, int  idPlanet) {
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
    }

    public String getName(){
        return this.name;
    }

    public int getIdCurrentPlanet() {
        return this.idCurrentPlanet;
    }

    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
