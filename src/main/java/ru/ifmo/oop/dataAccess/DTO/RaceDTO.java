package ru.ifmo.oop.dataAccess.DTO;

public class RaceDTO {
    public enum Behavior {
        ANGRY,
        NEUTRAL
    }
    private int id;
    private final String name;
    private final long amount;
    private final Behavior behavior;
    private int idCountry;

    RaceDTO(String name, long amount, boolean angry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        if (angry) behavior = Behavior.ANGRY;
        else behavior = Behavior.NEUTRAL;
    }

    public RaceDTO(String name, long amount, String behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        if (behavior.equals("ANGRY")) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
        this.idCountry = idCountry;
    }

    RaceDTO(int id, String name, long amount, boolean angry) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        if (angry) behavior = Behavior.ANGRY;
        else behavior = Behavior.NEUTRAL;
    }
    public RaceDTO(int id, String name, long amount, String behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        if (behavior.equals("ANGRY")) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
        this.idCountry = idCountry;
        this.id = id;
    }

    RaceDTO(int id, String name, long amount, String behavior) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        if (behavior.equals("ANGRY")) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
    }
    public RaceDTO(int id, String name, long amount, Behavior behavior, int idCountry) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }
    public RaceDTO(String name, long amount, Behavior behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }
    /*RaceDB(Race race) {
        this.name = race.getName();
        this.amount = race.getAmount();
        this.behavior = race.getBehavior();
    }*/

    public long getAmount() {
        return amount;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public boolean getBoolBehavior() {
        return (behavior == Behavior.ANGRY);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCountry() {
        return idCountry;
    }
}
