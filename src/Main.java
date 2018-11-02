import db.ExceptionDAO;
import terminal.PlanetManager;
import terminal.UserManager;
import ui.Console;

public class Main {

    public static void main(String[] args) throws ExceptionDAO {
        PlanetManager manager = new PlanetManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db");
        UserManager userManager = new UserManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db");
        Console console = new Console(manager,userManager,1);
        Thread loadPlanets = new Thread(console.new LoadPlanets());
        Thread loadingScreen = new Thread(console.new LoadingScreen());
        loadPlanets.start();
        loadingScreen.start();
        try {
            loadPlanets.join();
        } catch (InterruptedException e){
            return;
        }
        loadingScreen.interrupt();
        console.mainMenu();
    }
}
