package terminal;

import db.RaceDB;
import db.RaceDB.Behavior;

public class Race implements Searchable {
    private int id;
    private final String name;
    private final long amount;
    private final Behavior behavior;

    public Race(String name, long amount, boolean behavior) {
        this.name = name;
        this.amount = amount;
        if (behavior) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
    }
    public Race(int id, String name, long amount, boolean behavior) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        if (behavior) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
    }
    public Race(String name, long amount, Behavior behavior) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
    }
    public Race(int id, String name, long amount, Behavior behavior) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
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