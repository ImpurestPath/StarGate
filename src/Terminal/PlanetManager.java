package Terminal;

import DB.ExceptionDAO;
import DB.PlanetDAO;
import DB.PlanetDB;
import DB.XMLPlanetManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    private List<Planet> _planets;
    private PlanetDAO _db;

    public PlanetManager(String file) throws ExceptionDAO {
        this._db = new XMLPlanetManager(file);
        this._planets = new ArrayList<>();
        getAllPlanetsFromDB();
    }
    private void getAllPlanetsFromDB() throws ExceptionDAO {
        _planets.clear();
        for (PlanetDB planetDB :
                _db.getAll()) {
            _planets.add(new Planet(planetDB));
        }
    }
    public List<Planet> getAll(){
        return Collections.unmodifiableList(_planets);
    }
    public boolean isAngry(Planet planet) {
        if (planet != null) return planet.isAngry();
        else return false;
    }
    public void add(Planet planet) throws ExceptionDAO {
        planet.setId(_db.add(new PlanetDB(planet)));
        _planets.add(planet);
    }
    public Planet get(int id){
        for (Planet planet :
                _planets) {
            if (planet.getId() == id) return planet;
        }
        return null;
    }
    public boolean delete(int id) throws ExceptionDAO {
        boolean successful = _db.delete(id);
        if (successful){
            for (Planet planet :
                    _planets) {
                if (planet.getId() == id) {
                    _planets.remove(planet);
                    break;
                }
            }
        }
        return successful;
    }
    public boolean update(int id, Planet planet) throws ExceptionDAO {
        boolean successful = _db.update(id,new PlanetDB(planet));
        if (successful) {
            for (Planet planetItem :
                    _planets) {
                if (planetItem.getId() == id) planetItem = planet;
            }
        }
        return successful;
    }
    public int amountPlanets() {
        return _planets.size();
    }
    public boolean hasPlanet(int id) {
        return get(id) != null;
    }
}
