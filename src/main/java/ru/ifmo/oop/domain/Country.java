package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.DTO.RaceDTO.Behavior;

import java.util.List;

public class Country implements Comparable {
    private int id;
    private final String name;
    private final long area;
    private final long amountAlive;
    private final List<Race> races;
    private final Behavior behavior;
    private final int idPlanet;


    public Country(int id, String name, long area, List<Race> races, int idPlanet) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.races = races;
        amountAlive = calculateAmountAlive();
        this.behavior = calculateBehavior();
        this.idPlanet = idPlanet;
    }
    public Country(String name, long area, List<Race> races, int idPlanet) {
        this.id = -1;
        this.name = name;
        this.area = area;
        this.races = races;
        amountAlive = calculateAmountAlive();
        this.behavior = calculateBehavior();
        this.idPlanet = idPlanet;
    }

    private Behavior calculateBehavior() {
        if (amountAlive == 0) return Behavior.NEUTRAL;
        long amountAngry = 0;
        for (Race race : races) {
            if (race.getBehavior() == Behavior.ANGRY) amountAngry += race.getAmount();
        }
        if ((double) amountAngry / amountAlive > 0.5) return Behavior.ANGRY;
        else return Behavior.NEUTRAL;
    }

    private long calculateAmountAlive() {
        long amount = 0;
        for (Race race : races) {
            amount += race.getAmount();
        }
        return amount;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public long getArea() {
        return area;
    }

    public long getAmountAlive() {
        return amountAlive;
    }

    public List<Race> getRaces() {
        return races;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public boolean hasRaces() {
        return !races.isEmpty();
    }
    @Override
    public <T> boolean compare(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        if (id.getClass() == Integer.class){
            return Integer.toString(this.id).equals(id.toString());
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlanet() {
        return idPlanet;
    }

}
