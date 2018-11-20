package ru.ifmo.oop;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.domain.PlanetManager;
import ru.ifmo.oop.domain.UserManager;
import ru.ifmo.oop.ui.gui.PlanetGraphicsManager;
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
        MainGUI.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loading.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setMinHeight(720);
            stage.setMinWidth(1080);
            stage.setScene(scene);
            stage.show();
            Task load = PlanetGraphicsManager.getInstance().new Loader();
            LoadingController loadingController = loader.getController();
            loadingController.getProgressBar().progressProperty().bind(load.progressProperty());
            Thread t = new Thread(load);
            t.start();
            scenes = new ArrayList<>();
            next = 0;
            scenes.add(new Scene((new FXMLLoader(MainGUI.class.getResource("/fxml/auth.fxml"))).load()));
            FXMLLoader main = new FXMLLoader(MainGUI.class.getResource("/fxml/mainwindow.fxml"));
            scenes.add(new Scene(main.load()));
            mainWindowController = main.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Thread t2 = new Thread() {
            public void run() {
                try {
                    wait();
                    FXMLLoader main = new FXMLLoader(getClass().getResource("mainwindow.fxml"));
                    Parent root = main.load();
                    Scene mainWindow = new Scene(root);
                    stage.setScene(mainWindow);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Platform.runLater(t2);*/
    }
    public static void loadNext(){
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

    public static void main(String[] args) throws ExceptionDAO {
        new PlanetGraphicsManager(1,
                new PlanetManager("PlanetRepository.db"),
                new UserManager("PlanetRepository.db"));
        launch(args);

    }
}
