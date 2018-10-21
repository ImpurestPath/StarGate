package Terminal;

import DB.RaceDB;
import DB.RaceDB.Behavior;

public class Race implements WithName {

    private final String _name;
    private final long _amount;
    private final Behavior _behavior;

    public Race(String name, long amount, boolean angry) {
        this._name = name;
        this._amount = amount;
        if (angry) _behavior = Behavior.ANGRY;
        else _behavior = Behavior.NEUTRAL;
    }

    Race(RaceDB raceDB) {
        this._name = raceDB.getName();
        this._amount = raceDB.getAmount();
        if (raceDB.getBoolBehavior()) _behavior = Behavior.ANGRY;
        else _behavior = Behavior.NEUTRAL;
    }

    public long getAmount() {
        return _amount;
    }

    public Behavior getBehavior() {
        return _behavior;
    }

    public boolean getBoolBehavior() {
        return (_behavior == Behavior.ANGRY);
    }

    public String getName() {
        return _name;
    }
}