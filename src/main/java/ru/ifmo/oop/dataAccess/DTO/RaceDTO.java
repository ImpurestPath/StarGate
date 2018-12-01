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

/*    public RaceDTO(String name, long amount, String behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        if (behavior.equals("ANGRY")) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
        this.idCountry = idCountry;
    }*/

    public RaceDTO(int id, String name, long amount, String behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        if (behavior.equals("ANGRY")) this.behavior = Behavior.ANGRY;
        else this.behavior = Behavior.NEUTRAL;
        this.idCountry = idCountry;
        this.id = id;
    }

    public RaceDTO(int id, String name, long amount, Behavior behavior, int idCountry) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }
/*    public RaceDTO(String name, long amount, Behavior behavior, int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }*/

    public long getAmount() {
        return amount;
    }

    public Behavior getBehavior() {
        return behavior;
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
