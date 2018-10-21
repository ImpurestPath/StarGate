package DB;

import Terminal.Country;
import Terminal.Race;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CountryDB {
    private final String _name;
    private final long _area;
    private final List<RaceDB> _races;

    CountryDB(String name, long area, List<RaceDB> races) {
        this._name = name;
        this._area = area;
        this._races = races;
    }

    CountryDB(Country country) {
        this._name = country.getName();
        this._area = country.getArea();
        this._races = new ArrayList<>();
        for (Race race :
                country.getRaces()) {
            _races.add(new RaceDB(race));
        }
    }

    public long getArea() {
        return _area;
    }

    public List<RaceDB> getRaces() {
        // unmodifiable collection
        return Collections.unmodifiableList(_races);
    }

    public String getName() {
        return _name;
    }

}
