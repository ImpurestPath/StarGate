package ru.ifmo.oop.ui.gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.domain.User;
import ru.ifmo.oop.ui.gui.UIUserManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Label lblAttention;
    private UIUserManager userManager;

    public void setUserManager(UIUserManager userManager) {
        this.userManager = userManager;
    }

    private void cleanScene() {
        txtUsername.setText("");
        txtPassword.setText("");
        lblAttention.setText("");
    }

    public void btnSignInClicked(ActionEvent actionEvent) {
        if (txtUsername.getText().isEmpty()) return;
        try {
            // TODO Change to hash
            User potentialUser = userManager.get(txtUsername.getText().toLowerCase());
            if (potentialUser != null && potentialUser.getPassword().equals(txtPassword.getText())) {
                userManager.setCurrentUser(potentialUser);
                Platform.runLater(MainGUI::loadNext);
                cleanScene();
            } else {
                lblAttention.setText("No such user or wrong password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSignUpClicked(ActionEvent actionEvent) {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) return;
        try {
            userManager.add( //Sign up new user
                    new User(
                            txtUsername.getText(),
                            "",
                            userManager.getIdGatePlanet(),
                            txtPassword.getText()));
            lblAttention.setText("Successful. Please log in.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
