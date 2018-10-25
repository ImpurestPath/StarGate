import db.ExceptionDAO;
import terminal.PlanetManager;
import terminal.User;
import terminal.UserManager;
import ui.Console;

public class Main {

    public static void main(String[] args) throws ExceptionDAO {
        PlanetManager manager = new PlanetManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db");
        UserManager userManager = new UserManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db");
        Console console = new Console(manager,userManager);
        console.mainMenu();
    }
}
