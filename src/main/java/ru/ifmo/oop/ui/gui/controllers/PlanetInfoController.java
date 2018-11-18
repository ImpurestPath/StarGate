package ru.ifmo.oop.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.Language;
import ru.ifmo.oop.domain.Race;
import ru.ifmo.oop.ui.gui.PlanetGraphicsManager;
import ru.ifmo.oop.ui.gui.listCells.CountryListCell;
import ru.ifmo.oop.ui.gui.listCells.LanguageListCell;
import ru.ifmo.oop.ui.gui.PlanetGUI;
import ru.ifmo.oop.ui.gui.listCells.RaceListCell;

import java.io.IOException;
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
    private PlanetGUI planet;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setPlanet(PlanetGUI planet) {
        this.planet = planet;
        lblName.setText(planet.getName());
        lblPressure.setText(Long.toString(planet.getPressure()));
        lblAmount.setText(Long.toString(planet.getAmountAlive()));
        lblBehavior.setText(planet.getBehavior().toString());
        lblTemperature.setText(Integer.toString(planet.getTemperature()));
        lblArea.setText(Long.toString(planet.getArea()));
        List<Race> races = new ArrayList<>();
        for (Country country : planet.getCountries()) {
            races.addAll(country.getRaces());
        }
        listViewRaces.setItems(FXCollections.observableArrayList(races));
        listViewRaces.setCellFactory(params -> new RaceListCell());
        listViewLanguages.setItems(FXCollections.observableArrayList(planet.getLanguages()));
        listViewLanguages.setCellFactory(param -> new LanguageListCell());
        listViewCountries.setItems(FXCollections.observableArrayList(planet.getCountries()));
        listViewCountries.setCellFactory(params -> new CountryListCell());
    }

    public void btnBaсkClicked(ActionEvent actionEvent) {
        ((Stage) lblName.getScene().getWindow()).close();

    }

    public void btnAddLanguageClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(lblName.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/languagepage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            LanguagePageController languagePageController = loader.getController();
            languagePageController.setMode(LanguagePageController.Mode.CREATE);
            languagePageController.setIdPlanet(planet.getId());
            info.showAndWait();
            PlanetGraphicsManager.getInstance().updatePlanet(planet.getId());
            this.setPlanet(PlanetGraphicsManager.getInstance().getPlanet(planet.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEditLanguageClicked(ActionEvent actionEvent) {
        Language item = (Language)listViewLanguages.getSelectionModel().getSelectedItem();
        if (item != null) {
            Stage info = new Stage();
            info.initOwner(lblName.getScene().getWindow());
            info.initModality(Modality.APPLICATION_MODAL);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/languagepage.fxml"));
                Parent parent = loader.load();
                info.setScene(new Scene(parent));
                LanguagePageController languagePageController = loader.getController();
                languagePageController.setMode(LanguagePageController.Mode.UPDATE);
                languagePageController.setLanguage(item);
                languagePageController.setIdPlanet(planet.getId());
                info.showAndWait();
                PlanetGraphicsManager.getInstance().updatePlanet(planet.getId());
                this.setPlanet(PlanetGraphicsManager.getInstance().getPlanet(planet.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteLanguageClicked(ActionEvent actionEvent) {
        try{
            PlanetGraphicsManager.getInstance().deleteLanguageFromPlanet(planet.getId(),((Language)listViewLanguages.getSelectionModel().getSelectedItem()).getId());
            PlanetGraphicsManager.getInstance().updatePlanet(planet.getId());
            this.setPlanet(PlanetGraphicsManager.getInstance().getPlanet(planet.getId()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnAddCountryClicked(ActionEvent actionEvent) {
    }

    public void btnEditCountryClicked(ActionEvent actionEvent) {
    }

    public void btnDeleteCountryClicked(ActionEvent actionEvent) {
    }

    public void btnAddRaceClicked(ActionEvent actionEvent) {
    }

    public void btnEditRaceClicked(ActionEvent actionEvent) {
    }

    public void btnDeleteRaceClicked(ActionEvent actionEvent) {

    }
}
