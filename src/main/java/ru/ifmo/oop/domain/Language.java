package ru.ifmo.oop.domain;

import ru.ifmo.oop.dataAccess.DTO.LanguageDTO.Type;

public class Language implements Comparable {

    private int id;
    private final String name;
    private final boolean availableDictionary;
    private final Type type;
    private int idPlanet;

    public Language(String name, Type type, boolean availableDictionary,int idPlanet) {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
        this.idPlanet = idPlanet;
    }

    public Language(int id, String name, Type type, boolean availableDictionary, int idPlanet) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
        this.idPlanet = idPlanet;
    }


    public String merge() {
        return name;
    }

    public boolean isAvailableDictionary() {
        return availableDictionary;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MainLogic.Language{" +
                "name='" + name + '\'' +
                ", availableDictionary=" + availableDictionary +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> boolean compare(T id) {
        if (id.getClass() == String.class) {
            return this.name.equals(id.toString());
        }
        if (id.getClass() == Integer.class){
            return Integer.toString(this.id).equals(id.toString());
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlanet() {
        return idPlanet;
    }

    public void setIdPlanet(int idPlanet) {
        this.idPlanet = idPlanet;
    }
}
