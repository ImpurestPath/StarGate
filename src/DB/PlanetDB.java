package DB;


import Terminal.Country;
import Terminal.Language;
import Terminal.Planet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetDB {
    private final int _temperature;
    private final long _pressure;
    private int _id;
    private final String _name;
    private final List<LanguageDB> _languages;
    private final List<CountryDB> _countries;

    PlanetDB(String name, int temperature, long pressure,
             List<LanguageDB> languages, List<CountryDB> countries, int id) {
        this._name = name;
        this._temperature = temperature;
        this._pressure = pressure;
        this._languages = languages;
        this._countries = countries;
        this._id = id;
    }

    public PlanetDB(Planet planet) {
        this._name = planet.getName();
        this._temperature = planet.getTemperature();
        this._pressure = planet.getPressure();
        this._languages = new ArrayList<>();
        for (Language language :
                planet.getLanguages()) {
            _languages.add(new LanguageDB(language));
        }
        this._countries = new ArrayList<>();
        for (Country country :
                planet.getCountries()) {
            _countries.add(new CountryDB(country));
        }
        this._id = planet.getId();
    }

    public List<LanguageDB> getLanguages() {
        return Collections.unmodifiableList(_languages);
    }

    public List<CountryDB> getCountries() {
        return Collections.unmodifiableList(_countries);
    }

    public int getTemperature() {
        return _temperature;
    }

    public long getPressure() {
        return _pressure;
    }

    public String getName() {
        return _name;
    }

    public int getId() {
        return _id;
    }

    void setId(int id) {
        this._id = id;
    }

}
