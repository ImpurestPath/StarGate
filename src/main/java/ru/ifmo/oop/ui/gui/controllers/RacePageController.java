package ru.ifmo.oop.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.PlanetManager;
import ru.ifmo.oop.domain.Race;
import ru.ifmo.oop.ui.gui.UIPlanetRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RacePageController implements Initializable {
    public TextField txtName;
    public ChoiceBox chooseBehavior;
    public ComboBox chooseCountry;
    public TextField txtAmount;
    private int idPlanet;
    private Mode mode;
    private Race race;
    private List<Country> countries;
    private UIPlanetRepository planetManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseBehavior.setItems(FXCollections.observableArrayList());
        chooseBehavior.getItems().add(RaceDTO.Behavior.ANGRY);
        chooseBehavior.getItems().add(RaceDTO.Behavior.NEUTRAL);
    }

    public void setPlanetManager(UIPlanetRepository planetManager) {
        this.planetManager = planetManager;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        chooseCountry.setCellFactory(params -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country country, boolean empty) {
                super.updateItem(country, empty);
                if (country == null || empty) {
                    setText(null);
                } else {
                    setText(country.getName());
                }
            }
        });
        chooseCountry.setItems(FXCollections.observableArrayList(countries));
    }

    public void setIdPlanet(int idPlanet) {
        this.idPlanet = idPlanet;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setRace(Race race) {
        this.race = race;
        txtName.setText(race.getName());
        chooseBehavior.getSelectionModel().select(race.getBehavior() == RaceDTO.Behavior.ANGRY ? RaceDTO.Behavior.ANGRY : RaceDTO.Behavior.NEUTRAL);
        txtAmount.setText(Long.toString(race.getAmount()));
        chooseCountry.getSelectionModel().select(PlanetManager.find(countries, race.getIdCountry()));
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage) txtName.getScene().getWindow()).close();
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        try {
            if (mode == Mode.UPDATE) {
                Country selected = (Country) chooseCountry.getSelectionModel().getSelectedItem();
                RaceDTO.Behavior behavior = (RaceDTO.Behavior) chooseBehavior.getSelectionModel().getSelectedItem();
                planetManager.changeRaceOfCountry(idPlanet, selected.getId(), race.getId(), new Race(race.getId(), txtName.getText(), Long.parseLong(txtAmount.getText()), behavior, selected.getId()));
            } else {
                Country selected = (Country) chooseCountry.getSelectionModel().getSelectedItem();
                RaceDTO.Behavior behavior = (RaceDTO.Behavior) chooseBehavior.getSelectionModel().getSelectedItem();
                planetManager.addRaceToCountry(idPlanet, selected.getId(), new Race(txtName.getText(), Long.parseLong(txtAmount.getText()), behavior, selected.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Stage) txtName.getScene().getWindow()).close();
    }

    public enum Mode {
        UPDATE,
        CREATE
    }

}
