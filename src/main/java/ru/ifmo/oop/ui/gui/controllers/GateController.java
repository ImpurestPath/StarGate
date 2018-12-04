package ru.ifmo.oop.ui.gui.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.domain.StarGate;
import ru.ifmo.oop.domain.User;

import java.net.URL;
import java.util.ResourceBundle;

public class GateController implements Initializable {

    public ProgressBar progressBar;
    public Label lblName;
    private User user;
    private int idDestination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(30), e -> ((Stage) progressBar.getScene().getWindow()).close(),
                        new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setPlanet(String name, int id) {
        lblName.setText(name);
        this.idDestination = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void btnCancel(ActionEvent actionEvent) {
        ((Stage) progressBar.getScene().getWindow()).close();
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.M) {
            StarGate.moveUser(idDestination, user);
            Platform.runLater(MainGUI::loadNext);
            ((Stage) lblName.getScene().getWindow()).close();
        }
    }
}
