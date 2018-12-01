package ru.ifmo.oop.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WarningController implements Initializable {

    public Label lblWarning;
    public Label lblQuestion;
    boolean agree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setWarningName(String warning) {
        this.lblWarning.setText(warning);
    }

    void setQuestion(String question) {
        this.lblQuestion.setText(question);
    }

    boolean isAgree() {
        return agree;
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        agree = true;
        ((Stage) lblWarning.getScene().getWindow()).close();
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        agree = false;
        ((Stage) lblWarning.getScene().getWindow()).close();
    }
}
