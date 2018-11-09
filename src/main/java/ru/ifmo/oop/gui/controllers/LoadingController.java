package ru.ifmo.oop.gui.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable{
    public ProgressBar progBarLoad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progBarLoad.setProgress(0);
    }
    public ProgressBar getProgressBar(){
        return progBarLoad;
    }
}
