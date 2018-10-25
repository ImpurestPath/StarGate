import DB.ExceptionDAO;
import Terminal.PlanetManager;
import Terminal.User;
import UI.Console;

public class Main {

    public static void main(String[] args) throws ExceptionDAO {
        PlanetManager manager = new PlanetManager("C:\\Users\\romvr\\IdeaProjects\\StarGateNew\\PlanetRepository.db");
        User user = new User("Admin", manager.get(1));
        Console console = new Console(manager, user);
        console.mainMenu();
    }
}
