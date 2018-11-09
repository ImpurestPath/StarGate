package ru.ifmo.oop.ui.gui;


import ru.ifmo.oop.domain.Planet;

public class PlanetGUI {
    private final Planet planet;
    //private final Image image;
    PlanetGUI(Planet planet) {
        this.planet = planet;
        //this.image = new Image("Mustafar.jpg");
    }

    public Planet getPlanet() {
        return planet;
    }

    @Override
    public String toString() {
        return "PlanetGUI{" +
                "planet=" + planet.toString() +
                '}';
    }
}
