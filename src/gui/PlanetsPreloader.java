package gui;
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PlanetsPreloader extends Preloader {
    private Stage preloaderStage;
    LoadingController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("loading.fxml"));
        this.controller = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        controller.getProgressBar().setProgress(pn.getProgress());
    }
}