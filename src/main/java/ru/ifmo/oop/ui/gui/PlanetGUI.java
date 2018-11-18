package ru.ifmo.oop.ui.gui;


import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.Language;
import ru.ifmo.oop.domain.Planet;

import java.util.List;

public class PlanetGUI {
    //private final Planet planet;
    private final List<Language> languages;
    private final List<Country> countries;
    private final int id;
    private final long area;
    private final long amountAlive;
    private final int temperature;
    private final long pressure;
    private final RaceDTO.Behavior behavior;
    private final String name;

    //private final Image image;
    public PlanetGUI(int id,String name, long area, long amountAlive, int temperature, long pressure, RaceDTO.Behavior behavior, List<Language> languages, List<Country> countries) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.amountAlive = amountAlive;
        this.temperature = temperature;
        this.pressure = pressure;
        this.behavior = behavior;
        this.languages = languages;
        this.countries = countries;
        //this.image = new Image("Mustafar.jpg");
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public int getId() {
        return id;
    }

    public long getArea() {
        return area;
    }

    public long getAmountAlive() {
        return amountAlive;
    }

    public int getTemperature() {
        return temperature;
    }

    public long getPressure() {
        return pressure;
    }

    public RaceDTO.Behavior getBehavior() {
        return behavior;
    }

    public String getName() {
        return name;
    }
}
