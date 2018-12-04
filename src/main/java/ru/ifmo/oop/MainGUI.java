package ru.ifmo.oop;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.domain.PlanetManager;
import ru.ifmo.oop.domain.UserManager;
import ru.ifmo.oop.ui.gui.UIPlanetRepository;
import ru.ifmo.oop.ui.gui.UIUserManager;
import ru.ifmo.oop.ui.gui.controllers.AuthController;
import ru.ifmo.oop.ui.gui.controllers.LoadingController;
import ru.ifmo.oop.ui.gui.controllers.MainWindowController;

import java.util.ArrayList;
import java.util.List;

public class MainGUI extends Application {

    private static Stage stage;
    private static List<Scene> scenes;
    private static int next;
    private static MainWindowController mainWindowController;
    @Override
    public void start(Stage stage) throws Exception {
        UIPlanetRepository planetManager = new UIPlanetRepository(2,
                new PlanetManager("PlanetRepository.db"));
        UIUserManager userManager = new UIUserManager(2, new UserManager("PlanetRepository.db"));
        MainGUI.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loading.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setMinHeight(720);
            stage.setMinWidth(1080);
            stage.setScene(scene);
            stage.show();
            Task load = planetManager.new Loader(); // Loading planets process
            LoadingController loadingController = loader.getController();
            loadingController.getProgressBar().progressProperty().bind(load.progressProperty()); // Sync with loading
            Thread t = new Thread(load);
            t.start();
            scenes = new ArrayList<>();
            next = 0;
            FXMLLoader auth = new FXMLLoader(MainGUI.class.getResource("/fxml/auth.fxml")); // Auth scene
            scenes.add(new Scene(auth.load()));
            ((AuthController)auth.getController()).setUserManager(userManager);
            FXMLLoader main = new FXMLLoader(MainGUI.class.getResource("/fxml/mainwindow.fxml")); // Main scene
            scenes.add(new Scene(main.load()));
            mainWindowController = main.getController();
            mainWindowController.setPlanetManager(planetManager);
            mainWindowController.setUserManager(userManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadNext(){ // Change scenes in order (auth->main->)
        try {
            mainWindowController.updateMode();
            stage.setScene(scenes.get(next));
            next = (next+1)% scenes.size();
            //stage.setFullScreen(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DatabaseError {
        launch(args);
    }
}
