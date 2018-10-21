package DB;

import Terminal.Race;

public class RaceDB {
    public enum Behavior {
        ANGRY,
        NEUTRAL
    }

    private final String _name;
    private final long _amount;
    private final Behavior _behavior;

    RaceDB(String name, long amount, boolean angry) {
        this._name = name;
        this._amount = amount;
        if (angry) _behavior = Behavior.ANGRY;
        else _behavior = Behavior.NEUTRAL;
    }

    RaceDB(String name, long amount, String behavior) {
        this._name = name;
        this._amount = amount;
        if (behavior.equals("ANGRY")) _behavior = Behavior.ANGRY;
        else _behavior = Behavior.NEUTRAL;
    }

    RaceDB(Race race) {
        this._name = race.getName();
        this._amount = race.getAmount();
        this._behavior = race.getBehavior();
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
