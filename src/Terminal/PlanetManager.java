package Terminal;

import DB.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    private final PlanetDAO _db;

    public PlanetManager(String file) throws ExceptionDAO {
        this._db = new SQLPlanetManager(file);

    }

    private List<Planet> getAllFromDB() throws ExceptionDAO {
        List<Planet> planets = new ArrayList<>();
        for (PlanetDB planetDB :
                _db.getAll()) {
            planets.add(new Planet(planetDB)); //Mappers work
        }
    }

    public List<Planet> getAll() throws ExceptionDAO {
        return Collections.unmodifiableList(getAllFromDB());
    }

    public boolean isAngry(Planet planet) {
        if (planet != null) return planet.isAngry();
        else return false;
    }

    public void add(Planet planet) throws ExceptionDAO {
        planet.setId(_db.add(new PlanetDB(planet)));
    }

    public void delete(int id) throws ExceptionDAO {
        _db.delete(id);
    }

    public void update(int id, Planet planet) throws ExceptionDAO {
        _db.update(id, new PlanetDB(planet));
        planet.setId(id);
    }
}
