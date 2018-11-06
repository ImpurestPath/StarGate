package gui;

import db.ExceptionDAO;
import javafx.concurrent.Task;
import terminal.Planet;
import terminal.PlanetManager;
import terminal.User;
import terminal.UserManager;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final PlanetManager planetManager;
    private final UserManager userManager;
    private User user;
    private final int idGatePlanet;
    private List<PlanetGUI> planetUIList;
    private static Controller instance = null;
    public static synchronized Controller getInstance() throws Exception {
        if (instance == null) throw new Exception("No controller");
        else return instance;
    }
    Controller(int idGatePlanet, PlanetManager planetManager, UserManager userManager) {
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
                    Thread.sleep(3);
                }
            } catch (ExceptionDAO e) {
                System.out.println("Failure of loading");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
