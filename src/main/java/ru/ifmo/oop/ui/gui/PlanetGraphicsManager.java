package ru.ifmo.oop.ui.gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;
import ru.ifmo.oop.domain.*;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;
import ru.ifmo.oop.domain.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.List;

public class PlanetGraphicsManager {
    private final PlanetManager planetManager;
    private final UserManager userManager;
    private User user;
    private final int idGatePlanet;
    private List<PlanetGUI> planetUIList;
    private static PlanetGraphicsManager instance = null;
    public static synchronized PlanetGraphicsManager getInstance() {
        //if (instance == null) throw new Exception("No controller");
        //else
            return instance;
    }
    public PlanetGraphicsManager(int idGatePlanet, PlanetManager planetManager, UserManager userManager) {
        this.idGatePlanet = idGatePlanet;
        this.planetUIList = new ArrayList<>();
        this.planetManager = planetManager;
        this.userManager = userManager;
        instance = this;
    }

    public class Loader extends Task<Void> {
        @Override
        protected Void call() {
            try {
                List<Planet> planets = planetManager.getAll();
                int amount = planets.size();
                int i = 0;
                for (Planet planet :
                        planets) {
                    planetUIList.add(TransformerToGUI.toPlanet(planet));
                    i++;
                    this.updateProgress(i,amount);
                    //Thread.sleep(1);
                }

                Platform.runLater(MainGUI::loadMain);
            } catch (Exception e) {
                System.out.println("Failure of loading");
                e.printStackTrace();
            }
            return null;
        }
    }

    public void updatePlanet(int idPlanet) throws ExceptionDAO {
        int item = -1;
        int count = 0;
        for (PlanetGUI planetGUI : planetUIList){
            if (planetGUI.getId() == idPlanet){
                item = count;
                break;
            }
            count++;
        }
        if (item != -1){
            planetUIList.set(item, TransformerToGUI.toPlanet(planetManager.get(idPlanet)));
        }
    }
    public void changeLanguageOfPlanet(int idPlanet, int idLanguage, Language language) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().set(PlanetManager.findIndex(item.getLanguages(),idLanguage),language);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }
    public void addLanguageToPlanet(int idPlanet, Language language) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().add(language);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }
    public void deleteLanguageFromPlanet(int idPlanet,int idLanguage) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().remove(PlanetManager.findIndex(item.getLanguages(),idLanguage));
        planetManager.update(idPlanet,TransformerToEntity.toPlanet(item));
    }
    public void changeRaceOfCountry(int idPlanet, int idCountry, int idRace, Race race) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.set(PlanetManager.findIndex(races,idRace),race);
            item.getCountries().set(indexOfCountry, new Country(original.getId(),original.getName(),original.getArea(),races,original.getIdPlanet()));
            planetManager.update(idPlanet,TransformerToEntity.toPlanet(item));
        }
    }
    public void addRaceToCountry(int idPlanet, int idCountry, Race race) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.add(race);
            item.getCountries().set(indexOfCountry, new Country(original.getId(),original.getName(),original.getArea(),races,original.getIdPlanet()));
            planetManager.update(idPlanet,TransformerToEntity.toPlanet(item));
        }
    }
    public void deleteRaceFromCountry(int idPlanet, int idCountry, int idRace) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.remove(PlanetManager.find(races,idRace));
            item.getCountries().set(indexOfCountry, new Country(original.getId(),original.getName(),original.getArea(),races,original.getIdPlanet()));
            planetManager.update(idPlanet,TransformerToEntity.toPlanet(item));
        }
    }
    public void addCountryToPlanet(int idPlanet, Country country) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().add(country);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }
    public void changeCountryOfPlanet(int idPlanet, int idCountry, Country country) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().set(PlanetManager.findIndex(item.getCountries(),idCountry),country);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }
    public void deleteCountryFromPlanet(int idPlanet, int idCountry) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().remove(PlanetManager.findIndex(item.getCountries(),idCountry));
        planetManager.update(idPlanet,TransformerToEntity.toPlanet(item));
    }
    public List<PlanetGUI> getPlanetUIList() {
        return planetUIList;
    }

    public PlanetGUI getPlanet(int idPlanet){
        int item = -1;
        int count = 0;
        for (PlanetGUI planetGUI : planetUIList){
            if (planetGUI.getId() == idPlanet){
                item = count;
                break;
            }
            count++;
        }
        if (item != -1){
            return planetUIList.get(item);
        }
        else return null;
    }
}
