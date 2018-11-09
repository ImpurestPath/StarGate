package ru.ifmo.oop.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.Race;
import ru.ifmo.oop.ui.gui.listCells.CountryListCell;
import ru.ifmo.oop.ui.gui.listCells.LanguageListCell;
import ru.ifmo.oop.ui.gui.PlanetGUI;
import ru.ifmo.oop.ui.gui.listCells.RaceListCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlanetInfoController implements Initializable {

    public Label lblName;
    public Label lblPressure;
    public Label lblTemperature;
    public Label lblBehavior;
    public Label lblArea;
    public Label lblAmount;
    public ListView listViewLanguages;
    public ListView listViewCountries;
    public ListView listViewRaces;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setPlanet(PlanetGUI planet){
        lblName.setText(planet.getPlanet().getName());
        lblPressure.setText(Long.toString(planet.getPlanet().getPressure()));
        lblAmount.setText(Long.toString(planet.getPlanet().getAmountAlive()));
        lblBehavior.setText(planet.getPlanet().getBehavior().toString());
        lblTemperature.setText(Integer.toString(planet.getPlanet().getTemperature()));
        lblArea.setText(Long.toString(planet.getPlanet().getArea()));
        List<Race> races = new ArrayList<>();
        for (Country country: planet.getPlanet().getCountries()){
            races.addAll(country.getRaces());
        }
        listViewRaces.setItems(FXCollections.observableArrayList(races));
        listViewRaces.setCellFactory(params -> new RaceListCell());
        listViewLanguages.setItems(FXCollections.observableArrayList(planet.getPlanet().getLanguages()));
        listViewLanguages.setCellFactory(param -> new LanguageListCell());
        listViewCountries.setItems(FXCollections.observableArrayList(planet.getPlanet().getCountries()));
        listViewCountries.setCellFactory(params -> new CountryListCell());
        // TODO: make listViews
        // TODO: make languageListCell
        // TODO: make countryListCell
    }

    public void btnBaskClicked(ActionEvent actionEvent) {
        ((Stage) lblName.getScene().getWindow()).close();

    }
}
