package ru.ifmo.oop.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.ui.gui.UIPlanetManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CountryPageController implements Initializable {

    public TextField txtName;
    public TextField txtArea;
    private Country country;
    private int idPlanet;
    public enum Mode{
        UPDATE,
        CREATE
    }
    private Mode mode;


    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage) txtName.getScene().getWindow()).close();
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        try {
            if (mode == Mode.UPDATE) {
                UIPlanetManager.getInstance().changeCountryOfPlanet(idPlanet,
                        country.getId(),
                        new Country(txtName.getText(), Long.parseLong(txtArea.getText()), new ArrayList<>(), idPlanet));
            } else {
                UIPlanetManager.getInstance().addCountryToPlanet(idPlanet,
                        new Country(txtName.getText(), Long.parseLong(txtArea.getText()), new ArrayList<>(), idPlanet));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        ((Stage) txtName.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setCountry(Country country){
        this.country = country;
        this.txtName.setText(country.getName());
        this.txtArea.setText(Long.toString(country.getArea()));
    }

    public void setIdPlanet(int idPlanet) {
        this.idPlanet = idPlanet;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
