package ru.ifmo.oop.domain;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO.Behavior;

public class Race implements Comparable {
    private int id;
    private final String name;
    private final long amount;
    private final Behavior behavior;
    private final int idCountry;

    public Race(String name, long amount, Behavior behavior,int idCountry) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }
    public Race(int id, String name, long amount, Behavior behavior,int idCountry) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.behavior = behavior;
        this.idCountry = idCountry;
    }

    public long getAmount() {
        return amount;
    }

    public Behavior getBehavior() {
        return behavior;
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
    public <T> boolean compare(T id) {
        if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        if (id.getClass() == Integer.class){
            return Integer.toString(this.id).equals(id.toString());
        }
        return false;
    }

    public int getIdCountry() {
        return idCountry;
    }

}