package terminal;

public class User implements Searchable {
    private int id;
    private final String name;
    private final String permission;
    int idCurrentPlanet;

    public User(String name, String permission, int  idPlanet) {
        this.id = -1;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
        this.permission = permission;
    }
    public User(int id,String name, String permission, int  idPlanet) {
        this.id = id;
        this.name = name;
        this.idCurrentPlanet = idPlanet;
        this.permission = permission;
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
}
