package terminal;

import db.CountryDB;
import db.LanguageDB;
import db.PlanetDB;
import db.RaceDB.Behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Planet implements Searchable {
    private static final double AREAANGRYPERSENT = 0.4;
    private static final double AMOUNTANGRYPERSENT = 0.5;
    private final Behavior _behavior;
    private final int _temperature;
    private final long _pressure;
    private long _area;
    private long _amountAlive;
    private int _id;
    private final String _name;
    private final List<Language> _languages;
    private final List<Country> _countries;

    public Planet(int id, String name, int temperature, long pressure,
                  List<Language> languages, List<Country> countries) {
        this._id = id;
        this._name = name;
        this._temperature = temperature;
        this._pressure = pressure;
        this._languages = languages;
        this._countries = countries;
        calculateAreaAndAmount();
        this._behavior = calculateBehavior();
    }

    public Planet(String name, int temperature, long pressure,
                  List<Language> languages, List<Country> countries) {
        this._name = name;
        this._temperature = temperature;
        this._pressure = pressure;
        this._languages = languages;
        this._countries = countries;
        calculateAreaAndAmount();
        this._behavior = calculateBehavior();
        this._id = -1;
    }

    /*public Planet(PlanetDB planetDB) {
        this._name = planetDB.getName();
        this._temperature = planetDB.getTemperature();
        this._pressure = planetDB.getPressure();
        this._languages = new ArrayList<>();
        for (LanguageDB languageDB : planetDB.getLanguages()) {
            _languages.add(new Language(languageDB));
        }
        this._countries = new ArrayList<>();
        for (CountryDB countryDB : planetDB.getCountries()) {
            _countries.add(new Country(countryDB));
        }
        calculateAreaAndAmount();
        this._behavior = calculateBehavior();
        this._id = planetDB.getId();
    }*/

    private Behavior calculateBehavior() {
        if (_amountAlive == 0 || _area == 0) return Behavior.NEUTRAL;
        long areaAngry = 0,
                amountAngry = 0;
        for (Country country :
                _countries) {
            if (country.getBehavior() == Behavior.ANGRY) {
                areaAngry += country.getArea();
                amountAngry += country.getAmountAlive();
            }
        }
        if ((double) areaAngry / _area >= AREAANGRYPERSENT || (double) amountAngry / _amountAlive >= AMOUNTANGRYPERSENT)
            return Behavior.ANGRY;
        else return Behavior.NEUTRAL;
    }

    private void calculateAreaAndAmount() {
        long area = 0;
        long amount = 0;
        for (Country country :
                _countries) {
            area += country.getArea();
            amount += country.getAmountAlive();
        }
        _area = area;
        _amountAlive = amount;
    }

    public boolean isAngry() {
        return _behavior == Behavior.ANGRY;
    }

    public boolean hasLanguages() {
        return !_languages.isEmpty();
    }

    public boolean hasCountries() {
        return !_countries.isEmpty();
    }


    public List<Language> getLanguages() {
        return Collections.unmodifiableList(_languages);
    }

    public List<Country> getCountries() {
        return Collections.unmodifiableList(_countries);
    }

    public Behavior getBehavior() {
        return _behavior;
    }

    public int getTemperature() {
        return _temperature;
    }

    public long getPressure() {
        return _pressure;
    }


    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "MainLogic.Planet{" +
                ", _name='" + _name + '\'' +
                "_behavior=" + _behavior +
                ", _temperature=" + _temperature +
                ", _pressure=" + _pressure +
                ", _area=" + _area +
                ", _amountAlive=" + _amountAlive +
                ", _languages=" + _languages +
                ", _countries=" + _countries +
                '}';
    }
    public String getName() {
        return _name;
    }
    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == Integer.class){
            return Integer.toString(this._id).equals(id.toString());
        }
        else if (id.getClass() == String.class){
            return this._name.equals(id.toString());
        }
        else return false;
    }
}
