package gui;

import db.ExceptionDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import terminal.PlanetManager;
import terminal.UserManager;

import java.io.IOException;

public class MainGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Thread t = new Thread() {
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("loading.fxml"));
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
                    notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Platform.runLater(t);
        Thread t2 = new Thread() {
            public void run() {
                try {
                    wait();
                    FXMLLoader main = new FXMLLoader(getClass().getResource("mainwindow.fxml"));
                    Parent root = main.load();
                    Scene mainWindow = new Scene(root);
                    stage.setScene(mainWindow);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Platform.runLater(t2);
    }

    public static void main(String[] args) throws ExceptionDAO {
        new Controller(1, new PlanetManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db"), new UserManager("C:\\Users\\ImpurestPath\\IdeaProjects\\StarGate\\PlanetRepository.db"));
        launch(args);

    }
}
