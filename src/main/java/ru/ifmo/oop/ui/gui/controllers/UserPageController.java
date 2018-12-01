package ru.ifmo.oop.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.ui.gui.UIUserManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    public TextField txtNickname;
    public TextField txtPassword;
    public CheckBox checkBoxAdmin;
    private Mode mode;
    private User user;
    private UIUserManager userManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setUserManager(UIUserManager userManager) {
        this.userManager = userManager;
    }

    public void setUser(User user) {
        this.user = user;
        txtNickname.setText(user.getName());
        txtPassword.setText(user.getPassword());
        checkBoxAdmin.setSelected(user.getPermission().contains("a"));
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage) txtNickname.getScene().getWindow()).close();
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        try {
            if (mode == Mode.UPDATE) {
                userManager.update(
                        new User(
                                user.getId(),
                                txtNickname.getText(),
                                checkBoxAdmin.isSelected() ? "a" : "",
                                user.getIdCurrentPlanet(),
                                txtPassword.getText()));
            } else {
                userManager.add(
                        new User(
                                txtNickname.getText(),
                                checkBoxAdmin.isSelected() ? "a" : "",
                                -1,
                                txtPassword.getText()));
            }
            ((Stage) txtNickname.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Mode {
        CREATE,
        UPDATE
    }
}
