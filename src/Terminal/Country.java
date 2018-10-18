package Terminal;

import DB.CountryDB;
import DB.RaceDB;
import DB.RaceDB.Behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Country implements WithName{
    private String _name;
    private long _area;
    private long _amountAlive;
    private List<Race> _races;
    private Behavior _behavior;

    public Country(String name, long area, List<Race> races) {
        this._name = name;
        this._area = area;
        this._races = races;
        _amountAlive = calculateAmountAlive();
        this._behavior = calculateBehavior();
    }
    public Country(CountryDB countryDB){
        this._name = countryDB.getName();
        this._area = countryDB.getArea();
        this._races = new ArrayList<>();
        for (RaceDB raceDB :
                countryDB.getRaces()) {
            _races.add(new Race(raceDB));
        }
        this._amountAlive = calculateAmountAlive();
        this._behavior = calculateBehavior();
    }

    private Behavior calculateBehavior() {
        if (_amountAlive == 0) return Behavior.NEUTRAL;
        long amountAngry = 0;
        for (Race race : _races) {
            if (race.getBoolBehavior()) amountAngry += race.getAmount();
        }
        if ((double) amountAngry / _amountAlive > 0.5) return Behavior.ANGRY;
        else return Behavior.NEUTRAL;
    }

    private long calculateAmountAlive() {
        long amount = 0;
        for (Race race : _races) {
            amount += race.getAmount();
        }
        return amount;
    }

    public Behavior getBehavior() {
        return _behavior;
    }

    public long getArea() {
        return _area;
    }

    public long getAmountAlive() {
        return _amountAlive;
    }

    public List<Race> getRaces() {
        // unmodifiable collection
        return Collections.unmodifiableList(_races);
    }

    public String getName() {
        return _name;
    }

    public boolean hasRaces() {
        return !_races.isEmpty();
    }
}
