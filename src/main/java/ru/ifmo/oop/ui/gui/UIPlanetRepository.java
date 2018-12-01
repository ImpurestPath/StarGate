package ru.ifmo.oop.ui.gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.domain.*;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;
import ru.ifmo.oop.domain.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.List;

public class UIPlanetRepository {
    //TODO split functions
    private final PlanetManager planetManager;


    private final int idGatePlanet;
    private List<PlanetGUI> planetUIList;

    public UIPlanetRepository(int idGatePlanet, PlanetManager planetManager) {
        this.idGatePlanet = idGatePlanet;
        this.planetUIList = new ArrayList<>();
        this.planetManager = planetManager;
    }

    public class Loader extends Task<Void> {
        @Override
        protected Void call() {
            try {
                Task load = planetManager.new Loader();
                Thread t = new Thread(load);
                t.start();
                t.join();
                List<Planet> planets = (List<Planet>) load.get();
                int amount = planets.size();
                int i = 0;
                for (Planet planet :
                        planets) {
                    planetUIList.add(TransformerToGUI.toPlanet(planet));
                    i++;
                    this.updateProgress(i, amount);
                    //Thread.sleep(1);
                }
                Platform.runLater(MainGUI::loadNext);
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
        for (PlanetGUI planetGUI : planetUIList) {
            if (planetGUI.getId() == idPlanet) {
                item = count;
                break;
            }
            count++;
        }
        if (item != -1) {
            planetUIList.set(item, TransformerToGUI.toPlanet(planetManager.get(idPlanet)));
        }
    }

    public void changeLanguageOfPlanet(int idPlanet, int idLanguage, Language language) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().set(PlanetManager.findIndex(item.getLanguages(), idLanguage), language);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void addLanguageToPlanet(int idPlanet, Language language) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().add(language);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void deleteLanguageFromPlanet(int idPlanet, int idLanguage) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().remove(PlanetManager.findIndex(item.getLanguages(), idLanguage));
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void changeRaceOfCountry(int idPlanet, int idCountry, int idRace, Race race) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.set(PlanetManager.findIndex(races, idRace), race);
            item.getCountries().set(
                    indexOfCountry,
                    new Country(
                            original.getId(),
                            original.getName(),
                            original.getArea(),
                            races,
                            original.getIdPlanet()));
            planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
        }
    }

    public void addRaceToCountry(int idPlanet, int idCountry, Race race) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.add(race);
            item.getCountries().set(
                    indexOfCountry,
                    new Country(
                            original.getId(),
                            original.getName(),
                            original.getArea(),
                            races,
                            original.getIdPlanet()));
            planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
        }
    }

    public void deleteRaceFromCountry(int idPlanet, int idCountry, int idRace) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        int indexOfCountry = PlanetManager.findIndex(item.getCountries(), idCountry);
        if (indexOfCountry != -1) {
            Country original = item.getCountries().get(indexOfCountry);
            List<Race> races = new ArrayList<>(original.getRaces());
            races.remove(PlanetManager.find(races, idRace));
            item.getCountries().set(
                    indexOfCountry,
                    new Country(
                            original.getId(),
                            original.getName(),
                            original.getArea(),
                            races,
                            original.getIdPlanet()));
            planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
        }
    }

    public void addCountryToPlanet(int idPlanet, Country country) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().add(country);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void changeCountryOfPlanet(int idPlanet, int idCountry, Country country) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().set(PlanetManager.findIndex(item.getCountries(), idCountry), country);
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void deleteCountryFromPlanet(int idPlanet, int idCountry) throws ExceptionDAO {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().remove(PlanetManager.findIndex(item.getCountries(), idCountry));
        planetManager.update(idPlanet, TransformerToEntity.toPlanet(item));
    }

    public void addPlanet(Planet planet) throws ExceptionDAO {
        planetManager.add(planet);
        planetUIList.add(TransformerToGUI.toPlanet(planetManager.get(planet.getId())));
    }

    public void changePlanet(int idPlanet, Planet planet) throws ExceptionDAO {
        planetManager.update(idPlanet, planet);
        planetUIList.set(
                PlanetManager.findIndex(planetUIList, idPlanet),
                TransformerToGUI.toPlanet(planetManager.get(idPlanet)));
    }

    public void deletePlanet(Planet planet) throws ExceptionDAO {
        planetManager.delete(planet);
        planetUIList.remove(PlanetManager.find(planetUIList, planet.getId()));
    }


    public List<PlanetGUI> getPlanetUIList() {
        return planetUIList;
    }

    public PlanetGUI getPlanet(int idPlanet) {
        return PlanetManager.find(planetUIList, idPlanet);
    }

    public int getIdGatePlanet() {
        return idGatePlanet;
    }
}
