package ru.ifmo.oop.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.ui.gui.UIUserManager;


public class FindUserController {
    public enum Mode {
        ONLY_FIND,
        RETURN_USER
    }

    public TextField txtUsername;
    public Label lblCurrentPlanet;
    public Label lblPermissions;
    public Button btnOk;
    public Button btnCancel;
    private UIUserManager userManager;
    private User currentUser;
    private Mode mode;

    public void setMode(Mode mode) {
        this.mode = mode;
        if (mode == Mode.ONLY_FIND) {
            btnOk.setVisible(false);
            btnCancel.setText("Back");
        }
    }

    public void btnFindClicked(ActionEvent actionEvent) {
        try {
            currentUser = userManager.get(txtUsername.getText().toLowerCase());
            if (currentUser != null) {
                lblCurrentPlanet.setText(Integer.toString(currentUser.getIdCurrentPlanet()));
                lblPermissions.setText(currentUser.getPermission().isEmpty() ? "No special" : currentUser.getPermission());
            } else lblCurrentPlanet.setText("No such user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserManager(UIUserManager userManager) {
        this.userManager = userManager;
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage) lblCurrentPlanet.getScene().getWindow()).close();
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        if (currentUser == null) lblCurrentPlanet.setText("Cannot exit without found user");
        else ((Stage) lblCurrentPlanet.getScene().getWindow()).close();
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
