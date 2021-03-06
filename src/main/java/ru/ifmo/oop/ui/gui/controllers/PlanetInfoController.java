package ru.ifmo.oop.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ifmo.oop.domain.Country;
import ru.ifmo.oop.domain.Language;
import ru.ifmo.oop.domain.Race;
import ru.ifmo.oop.ui.gui.UIPlanetRepository;
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
    public ToolBar toolsLanguages;
    public ToolBar toolsCountries;
    public ToolBar toolsRaces;
    public ImageView imageView;
    private PlanetGUI planet;
    private UIPlanetRepository planetManager;
    public enum Mode{
        VIEW,
        CHANGE
    }
    private Mode mode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setPlanetManager(UIPlanetRepository planetManager) {
        this.planetManager = planetManager;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        if (mode == Mode.VIEW){
            toolsLanguages.setVisible(false);
            toolsCountries.setVisible(false);
            toolsRaces.setVisible(false);
        }
    }

    public void setPlanet(PlanetGUI planet) {
        this.planet = planet;
        imageView.setImage(planet.getImage());
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
            languagePageController.setPlanetManager(planetManager);
            info.showAndWait();
            planetManager.updatePlanet(planet.getId());
            this.setPlanet(planetManager.getPlanet(planet.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEditLanguageClicked(ActionEvent actionEvent) {
        Language item = (Language) listViewLanguages.getSelectionModel().getSelectedItem();
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
                languagePageController.setPlanetManager(planetManager);
                info.showAndWait();
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteLanguageClicked(ActionEvent actionEvent) {
        try {
            Language item = (Language) listViewLanguages.getSelectionModel().getSelectedItem();
            if (item != null) {
                planetManager.deleteLanguageFromPlanet(planet.getId(), item.getId());
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddCountryClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(lblName.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/countrypage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            CountryPageController countryPageController = loader.getController();
            countryPageController.setMode(CountryPageController.Mode.CREATE);
            countryPageController.setIdPlanet(planet.getId());
            countryPageController.setPlanetManager(planetManager);
            info.showAndWait();
            planetManager.updatePlanet(planet.getId());
            this.setPlanet(planetManager.getPlanet(planet.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEditCountryClicked(ActionEvent actionEvent) {
        Country item = (Country) listViewCountries.getSelectionModel().getSelectedItem();
        if (item != null) {
            Stage info = new Stage();
            info.initOwner(lblName.getScene().getWindow());
            info.initModality(Modality.APPLICATION_MODAL);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/countrypage.fxml"));
                Parent parent = loader.load();
                info.setScene(new Scene(parent));
                CountryPageController countryPageController = loader.getController();
                countryPageController.setMode(CountryPageController.Mode.UPDATE);
                countryPageController.setCountry(item);
                countryPageController.setIdPlanet(planet.getId());
                countryPageController.setPlanetManager(planetManager);
                info.showAndWait();
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteCountryClicked(ActionEvent actionEvent) {
        try {
            Country item = (Country) listViewCountries.getSelectionModel().getSelectedItem();
            if (item != null) {
                planetManager.deleteCountryFromPlanet(planet.getId(), item.getId());
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddRaceClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(lblName.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/racepage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            RacePageController racePageController = loader.getController();
            racePageController.setMode(RacePageController.Mode.CREATE);
            racePageController.setIdPlanet(planet.getId());
            racePageController.setCountries(planet.getCountries());
            racePageController.setPlanetManager(planetManager);
            info.showAndWait();
            planetManager.updatePlanet(planet.getId());
            this.setPlanet(planetManager.getPlanet(planet.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEditRaceClicked(ActionEvent actionEvent) {
        Race item = (Race) listViewRaces.getSelectionModel().getSelectedItem();
        if (item != null) {
            Stage info = new Stage();
            info.initOwner(lblName.getScene().getWindow());
            info.initModality(Modality.APPLICATION_MODAL);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/racepage.fxml"));
                Parent parent = loader.load();
                info.setScene(new Scene(parent));
                RacePageController racePageController = loader.getController();
                racePageController.setMode(RacePageController.Mode.UPDATE);
                racePageController.setIdPlanet(planet.getId());
                racePageController.setCountries(planet.getCountries());
                racePageController.setRace(item);
                racePageController.setPlanetManager(planetManager);
                info.showAndWait();
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteRaceClicked(ActionEvent actionEvent) {
        try {
            Race item = (Race) listViewRaces.getSelectionModel().getSelectedItem();
            if (item != null) {
                planetManager.deleteRaceFromCountry(planet.getId(), item.getIdCountry(), item.getId());
                planetManager.updatePlanet(planet.getId());
                this.setPlanet(planetManager.getPlanet(planet.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
