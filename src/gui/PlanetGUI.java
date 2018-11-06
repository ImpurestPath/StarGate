package gui;

import terminal.Planet;
import javafx.scene.image.*;


public class PlanetGUI {
    private final Planet planet;
    //private final Image image;
    PlanetGUI(Planet planet) {
        this.planet = planet;
        //this.image = new Image("Mustafar.jpg");
    }

    Planet getPlanet() {
        return planet;
    }
}
