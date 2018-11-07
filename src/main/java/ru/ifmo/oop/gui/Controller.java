package ru.ifmo.oop.gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.domain.Planet;
import ru.ifmo.oop.domain.PlanetManager;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.domain.UserManager;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final PlanetManager planetManager;
    private final UserManager userManager;
    private User user;
    private final int idGatePlanet;
    private List<PlanetGUI> planetUIList;
    private static Controller instance = null;
    public static synchronized Controller getInstance() {
        //if (instance == null) throw new Exception("No controller");
        //else
            return instance;
    }
    public Controller(int idGatePlanet, PlanetManager planetManager, UserManager userManager) {
        this.idGatePlanet = idGatePlanet;
        this.planetUIList = new ArrayList<>();
        this.planetManager = planetManager;
        this.userManager = userManager;
        instance = this;
    }

    public class Loader extends Task<Void> {
        @Override
        protected Void call() {
            try {
                List<Planet> planets = planetManager.getAll();
                int amount = planets.size();
                int i = 0;
                for (Planet planet :
                        planets) {
                    planetUIList.add(new PlanetGUI(planet));
                    i++;
                    this.updateProgress(i,amount);
                    Thread.sleep(1);
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        MainGUI.loadMain();
                    }
                });
            } catch (Exception e) {
                System.out.println("Failure of loading");
                e.printStackTrace();
            }
            return null;
        }
    }

    public PlanetManager getPlanetManager() {
        return planetManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public User getUser() {
        return user;
    }

    public int getIdGatePlanet() {
        return idGatePlanet;
    }

    public List<PlanetGUI> getPlanetUIList() {
        return planetUIList;
    }
}
