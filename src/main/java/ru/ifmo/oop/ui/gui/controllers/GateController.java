package ru.ifmo.oop.ui.gui.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GateController implements Initializable {

    public ProgressBar progressBar;
    public Label lblName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(30), e-> ((Stage)progressBar.getScene().getWindow()).close(),
                        new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setPlanetName(String name){
        lblName.setText(name);
    }
    public void btnCancel(ActionEvent actionEvent) {
        ((Stage) progressBar.getScene().getWindow()).close();
    }
}
