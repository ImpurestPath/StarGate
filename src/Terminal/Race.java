package Terminal;

import DB.RaceDB;
import DB.RaceDB.Behavior;

public class Race implements Searchable {
    private int id;
    private final String name;
    private final long amount;
    private final Behavior behavior;

    public Race(String name, long amount, boolean angry) {
        this.name = name;
        this.amount = amount;
        if (angry) behavior = Behavior.ANGRY;
        else behavior = Behavior.NEUTRAL;
    }
    public Race(int id, String name, long amount, boolean angry) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        if (angry) behavior = Behavior.ANGRY;
        else behavior = Behavior.NEUTRAL;
    }

    Race(RaceDB raceDB) {
        this.name = raceDB.getName();
        this.amount = raceDB.getAmount();
        if (raceDB.getBoolBehavior()) behavior = Behavior.ANGRY;
        else behavior = Behavior.NEUTRAL;
    }

    public long getAmount() {
        return amount;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public boolean getBoolBehavior() {
        return (behavior == Behavior.ANGRY);
    }
    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        return false;
    }
}