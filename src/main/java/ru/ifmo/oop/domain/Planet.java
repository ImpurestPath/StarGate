package ru.ifmo.oop.domain;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO.Behavior;

import java.util.Collections;
import java.util.List;

public class Planet implements Comparable {
    private static final double AREAANGRYPERSENT = 0.4;
    private static final double AMOUNTANGRYPERSENT = 0.5;
    private final Behavior behavior;
    private final int temperature;
    private final long pressure;
    private long area;
    private long amountAlive;
    private int id;
    private final String name;
    private final List<Language> languages;
    private final List<Country> countries;

    public Planet(int id, String name, int temperature, long pressure,
                  List<Language> languages, List<Country> countries) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.languages = languages;
        this.countries = countries;
        calculateAreaAndAmount();
        this.behavior = calculateBehavior();
    }

    public Planet(String name, int temperature, long pressure,
                  List<Language> languages, List<Country> countries) {
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.languages = languages;
        this.countries = countries;
        calculateAreaAndAmount();
        this.behavior = calculateBehavior();
        this.id = -1;
    }

    private Behavior calculateBehavior() {
        if (amountAlive == 0 || area == 0) return Behavior.NEUTRAL;
        long areaAngry = 0,
                amountAngry = 0;
        for (Country country :
                countries) {
            if (country.getBehavior() == Behavior.ANGRY) {
                areaAngry += country.getArea();
                amountAngry += country.getAmountAlive();
            }
        }
        if ((double) areaAngry / area >= AREAANGRYPERSENT || (double) amountAngry / amountAlive >= AMOUNTANGRYPERSENT)
            return Behavior.ANGRY;
        else return Behavior.NEUTRAL;
    }

    private void calculateAreaAndAmount() {
        long area = 0;
        long amount = 0;
        for (Country country :
                countries) {
            area += country.getArea();
            amount += country.getAmountAlive();
        }
        this.area = area;
        this.amountAlive = amount;
    }

    public boolean isAngry() {
        return behavior == Behavior.ANGRY;
    }

    public boolean hasLanguages() {
        return !languages.isEmpty();
    }

    public boolean hasCountries() {
        return !countries.isEmpty();
    }


    public List<Language> getLanguages() {
        return Collections.unmodifiableList(languages);
    }

    public List<Country> getCountries() {
        return Collections.unmodifiableList(countries);
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public int getTemperature() {
        return temperature;
    }

    public long getPressure() {
        return pressure;
    }

    public long getArea() {
        return area;
    }

    public long getAmountAlive() {
        return amountAlive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("MainLogic.Planet{name='%s', behavior=%s, temperature=%d, pressure=%d, area=%d, amountAlive=%d, languages=%s, countries=%s}",
                name, behavior, temperature, pressure, area, amountAlive, languages, countries);
    }
    public String getName() {
        return name;
    }
    @Override
    public <T> boolean compare(T id) {
        if (id.getClass() == Integer.class){
            return Integer.toString(this.id).equals(id.toString());
        }
        else if (id.getClass() == String.class){
            return this.name.equals(id.toString());
        }
        else return false;
    }
}
