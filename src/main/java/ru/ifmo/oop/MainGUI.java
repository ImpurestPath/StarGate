package ru.ifmo.oop;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.ifmo.oop.db.Exception.ExceptionDAO;
import ru.ifmo.oop.domain.PlanetManager;
import ru.ifmo.oop.domain.UserManager;
import ru.ifmo.oop.gui.Controller;
import ru.ifmo.oop.gui.controllers.LoadingController;

public class MainGUI extends Application {
    public static Stage stage;
    public static Scene nextScene;
    public static boolean ready;
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
            Task load = Controller.getInstance().new Loader();
            LoadingController loadingController = loader.getController();
            loadingController.getProgressBar().progressProperty().bind(load.progressProperty());
            Thread t = new Thread(load);
            t.start();
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
    public static void loadMain() {
        try {
            FXMLLoader main = new FXMLLoader(MainGUI.class.getResource("/fxml/mainwindow.fxml"));

            Parent root = main.load();
            Scene mainWindow = new Scene(root);
            stage.setScene(mainWindow);
            stage.setFullScreen(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void stageShow(){
        stage.show();
    }
    public static void main(String[] args) throws ExceptionDAO {
        new Controller(1,
                new PlanetManager("PlanetRepository.db"),
                new UserManager("PlanetRepository.db"));
        launch(args);

    }
}