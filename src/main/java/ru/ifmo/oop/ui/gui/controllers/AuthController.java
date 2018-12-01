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

    public void btnSignInClicked(ActionEvent actionEvent) {
        try {
            // TODO Change to hash
            User potentialUser = userManager.getUser(txtUsername.getText().toLowerCase());
            if (potentialUser != null && potentialUser.getPassword().equals(txtPassword.getText())){
                userManager.setUser(potentialUser);
                Platform.runLater(MainGUI::loadNext);
                txtUsername.setText("");
                txtPassword.setText("");
                lblAttention.setText("");
            }
            else {
                lblAttention.setText("No such user or wrong password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSignUpClicked(ActionEvent actionEvent) {
        try {
            userManager.addUser(
                    new User(
                            txtUsername.getText(),
                            "",
                            userManager.getIdGatePlanet(),
                            txtPassword.getText()));
            Platform.runLater(MainGUI::loadNext);
            txtUsername.setText("");
            txtPassword.setText("");
            lblAttention.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
