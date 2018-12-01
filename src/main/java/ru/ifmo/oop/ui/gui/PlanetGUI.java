package ru.ifmo.oop.ui.gui;


import javafx.scene.image.Image;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.domain.Comparable;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.Language;
import ru.ifmo.oop.domain.Planet;


import java.util.List;

public class PlanetGUI implements Comparable {
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
    private final Image image;

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
        this.image = new Image("/images/planet.jpg");
    }
/*    public PlanetGUI(String name, long area, long amountAlive, int temperature, long pressure, RaceDTO.Behavior behavior, List<Language> languages, List<Country> countries) {
        this.id = -1;
        this.name = name;
        this.area = area;
        this.amountAlive = amountAlive;
        this.temperature = temperature;
        this.pressure = pressure;
        this.behavior = behavior;
        this.languages = languages;
        this.countries = countries;
        this.image = new Image("/images/planet.jpg");
    }*/
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

    public Image getImage() {
        return image;
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
