package ru.ifmo.oop.mappers;

import ru.ifmo.oop.domain.Planet;
import ru.ifmo.oop.ui.gui.PlanetGUI;

import java.util.ArrayList;

public class TransformerToGUI {
    public static PlanetGUI toPlanet(Planet planet){
        return new PlanetGUI(planet.getId(),planet.getName(),planet.getArea(),planet.getAmountAlive(),planet.getTemperature(),planet.getPressure(),planet.getBehavior(),new ArrayList<>(planet.getLanguages()),new ArrayList<>(planet.getCountries()));
    }
}
