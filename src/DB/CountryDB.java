package DB;

import Terminal.Country;
import Terminal.Race;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CountryDB {


    private int id;
    private final String _name;
    private final long _area;

    CountryDB(int id, String name, long area) {
        this.id = id;
        this._name = name;
        this._area = area;
    }
    CountryDB(String name, long area) {
        this.id = -1;
        this._name = name;
        this._area = area;
    }

    CountryDB(Country country) {
        this.id = country.getId();
        this._name = country.getName();
        this._area = country.getArea();
    }

    public long getArea() {
        return _area;
    }

    public String getName() {
        return _name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
