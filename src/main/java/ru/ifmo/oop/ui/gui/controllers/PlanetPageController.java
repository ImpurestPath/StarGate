package ru.ifmo.oop.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.Planet;
import ru.ifmo.oop.ui.gui.PlanetGUI;
import ru.ifmo.oop.ui.gui.UIPlanetManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlanetPageController implements Initializable {
    public TextField txtPressure;
    public TextField txtTemperature;
    public TextField txtName;
    private PlanetGUI planet;
    public enum Mode{
        CREATE,
        UPDATE
    }
    private Mode mode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void setPlanet(PlanetGUI planet){
        this.planet = planet;
        this.txtName.setText(planet.getName());
        this.txtTemperature.setText(Integer.toString(planet.getTemperature()));
        this.txtPressure.setText(Long.toString(planet.getPressure()));
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        try {
            if (mode == Mode.UPDATE) {
                UIPlanetManager.getInstance().changePlanet(planet.getId(),new Planet(txtName.getText(), Integer.parseInt(txtTemperature.getText()), Long.parseLong(txtPressure.getText()), new ArrayList<>(), new ArrayList<>()));
            } else {
                UIPlanetManager.getInstance().addPlanet(new Planet(txtName.getText(), Integer.parseInt(txtTemperature.getText()), Long.parseLong(txtPressure.getText()), new ArrayList<>(), new ArrayList<>()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        ((Stage)txtName.getScene().getWindow()).close();
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage)txtName.getScene().getWindow()).close();
    }
}
