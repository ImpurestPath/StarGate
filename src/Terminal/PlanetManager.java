package Terminal;

import DB.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    private List<Planet> _planets;
    private final PlanetDAO _db;

    public PlanetManager(String file) throws ExceptionDAO {
        this._db = new SQLitePlanetManager(file);
        this._planets = new ArrayList<>();
        getAllFromDB();
    }

    private void getAllFromDB() throws ExceptionDAO {
        _planets.clear();
        for (PlanetDB planetDB :
                _db.getAll()) {
            _planets.add(new Planet(planetDB));
        }
    }

    public List<Planet> getAll() {
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

    public Planet get(int id) {
        for (Planet planet :
                _planets) {
            if (planet.getId() == id) return planet;
        }
        return null;
    }

    public void delete(int id) throws ExceptionDAO {
        _db.delete(id);
        for (Planet planet :
                _planets) {
            if (planet.getId() == id) {
                _planets.remove(planet);
                break;
            }
        }
    }

    public void update(int id, Planet planet) throws ExceptionDAO {
        _db.update(id, new PlanetDB(planet));
        planet.setId(id);
        int count = 0;
        for (Planet planetItem :
                _planets) {
            if (planetItem.getId() == id) {
                _planets.set(count, planet);
                break;
            }
            count++;
        }
    }

    public boolean hasPlanet(int id) {
        return get(id) != null;
    }
}
