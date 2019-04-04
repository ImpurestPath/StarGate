package ru.ifmo.oop.ui.gui;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.domain.*;
import ru.ifmo.oop.domain.interfaces.Listener;
import ru.ifmo.oop.mappers.TransformerToEntity;
import ru.ifmo.oop.mappers.TransformerToGUI;

import java.util.ArrayList;
import java.util.List;

public class UIPlanetRepository {
    private final PlanetManager planetManager;
    private final int idGatePlanet;
    private List<PlanetGUI> planetUIList;

    public UIPlanetRepository(int idGatePlanet, PlanetManager planetManager) {
        this.idGatePlanet = idGatePlanet;
        this.planetUIList = new ArrayList<>();
        this.planetManager = planetManager;
    }

    public Observable<List<PlanetGUI>> load() {
        Observable<List<PlanetGUI>> observable = new Observable<>() {
            @Override
            public List<PlanetGUI> mainActivity() throws Exception {
                planetUIList = new ArrayList<>();
                Observable<List<Planet>> fromDB = planetManager.getAll();
                fromDB.addListener(new Listener() {
                    @Override
                    public void handle(double progress) {
                        notifyListeners(progress / 2);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
                fromDB.call();
                List<Planet> planets = fromDB.getResult();
                int amount = planets.size();
                for (Planet planet :
                        planets) {
                    planetUIList.add(TransformerToGUI.toPlanet(planet));
                    notifyListeners((double) planetUIList.size() / amount / 2 + 0.5);
                    Thread.sleep(1000);
                }
                finishListeners();
                return planetUIList;
            }

        };
        return observable;
    }

    public void updatePlanet(int idPlanet) throws DatabaseError {
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

    public void changeLanguageOfPlanet(int idPlanet, int idLanguage, Language language) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().set(PlanetManager.findIndex(item.getLanguages(), idLanguage), language);
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void addLanguageToPlanet(int idPlanet, Language language) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().add(language);
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void deleteLanguageFromPlanet(int idPlanet, int idLanguage) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getLanguages().remove(PlanetManager.findIndex(item.getLanguages(), idLanguage));
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void changeRaceOfCountry(int idPlanet, int idCountry, int idRace, Race race) throws DatabaseError {
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
            planetManager.update(TransformerToEntity.toPlanet(item));
        }
    }

    public void addRaceToCountry(int idPlanet, int idCountry, Race race) throws DatabaseError {
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
            planetManager.update(TransformerToEntity.toPlanet(item));
        }
    }

    public void deleteRaceFromCountry(int idPlanet, int idCountry, int idRace) throws DatabaseError {
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
            planetManager.update(TransformerToEntity.toPlanet(item));
        }
    }

    public void addCountryToPlanet(int idPlanet, Country country) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().add(country);
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void changeCountryOfPlanet(int idPlanet, int idCountry, Country country) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().set(PlanetManager.findIndex(item.getCountries(), idCountry), country);
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void deleteCountryFromPlanet(int idPlanet, int idCountry) throws DatabaseError {
        PlanetGUI item = getPlanet(idPlanet);
        item.getCountries().remove(PlanetManager.findIndex(item.getCountries(), idCountry));
        planetManager.update(TransformerToEntity.toPlanet(item));
    }

    public void addPlanet(Planet planet) throws DatabaseError {
        planetManager.add(planet);
        planetUIList.add(TransformerToGUI.toPlanet(planetManager.get(planet.getId())));
    }

    public void changePlanet(int idPlanet, Planet planet) throws DatabaseError {
        planetManager.update(planet);
        planetUIList.set(
                PlanetManager.findIndex(planetUIList, idPlanet),
                TransformerToGUI.toPlanet(planetManager.get(idPlanet)));
    }

    public void deletePlanet(Planet planet) throws DatabaseError {
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
