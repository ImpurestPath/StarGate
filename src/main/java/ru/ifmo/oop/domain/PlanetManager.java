package ru.ifmo.oop.domain;


import javafx.concurrent.Task;
import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.exception.ConnectionError;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.dataAccess.SQL.SQLConnection;
import ru.ifmo.oop.domain.mappers.TransformerToDTO;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    //TODO make interface
    private final ConnectionDAO connection;

    public PlanetManager(String filename) throws ConnectionError {
        this.connection = SQLConnection.getInstance(filename);
    }

    private Planet buildPlanet(int idPlanet) throws DatabaseError {
        PlanetDTO planetDTO = connection.getPlanet(idPlanet);
        List<CountryDTO> countriesDB = connection.getPlanetCountries(planetDTO.getId());
        List<Country> countries = new ArrayList<>();
        for (CountryDTO countryDTO :
                countriesDB) {
            List<RaceDTO> racesDB = connection.getCountryRaces(countryDTO.getId());
            List<Race> races = new ArrayList<>();
            for (RaceDTO raceDTO :
                    racesDB) {
                races.add(TransformerToEntity.toRace(raceDTO));
            }
            countries.add(TransformerToEntity.toCountry(countryDTO, races));
        }
        List<LanguageDTO> languagesDB = connection.getPlanetLanguages(planetDTO.getId());
        List<Language> languages = new ArrayList<>();
        for (LanguageDTO languageDTO :
                languagesDB) {
            languages.add(TransformerToEntity.toLanguage(languageDTO));
        }
        return TransformerToEntity.toPlanet(planetDTO, languages, countries);
    }
    //TODO task progress
    public class Loader extends Task<List<Planet>>{
        @Override
        protected List<Planet> call() throws DatabaseError {
            List<Planet> planets = new ArrayList<>();
            for (PlanetDTO planetDTO :
                    connection.getAllPlanets()) {
                planets.add(buildPlanet(planetDTO.getId())); //mappers work
            }
            return Collections.unmodifiableList(planets);
        }
    }
//    public List<Planet> getAll() throws ExceptionDAO {
//        List<Planet> planets = new ArrayList<>();
//        for (PlanetDTO planetDTO :
//                connection.getAllPlanets()) {
//            List<CountryDTO> countriesDB = connection.getPlanetCountries(planetDTO.getId());
//            List<Country> countries = new ArrayList<>();
//            for (CountryDTO countryDTO :
//                    countriesDB) {
//                List<RaceDTO> racesDB = connection.getCountryRaces(countryDTO.getId());
//                List<Race> races = new ArrayList<>();
//                for (RaceDTO raceDTO :
//                        racesDB) {
//                    races.add(TransformerToEntity.toRace(raceDTO));
//                }
//                countries.add(TransformerToEntity.toCountry(countryDTO, races));
//            }
//            List<LanguageDTO> languagesDB = connection.getPlanetLanguages(planetDTO.getId());
//            List<Language> languages = new ArrayList<>();
//            for (LanguageDTO languageDTO :
//                    languagesDB) {
//                languages.add(TransformerToEntity.toLanguage(languageDTO));
//            }
//            planets.add(TransformerToEntity.toPlanet(planetDTO, languages, countries)); //mappers work
//        }
//        return Collections.unmodifiableList(planets);
//    }

    public Planet get(int idPlanet) throws DatabaseError {
        return buildPlanet(idPlanet);
    }

    public void add(Planet planet) throws DatabaseError {
        planet.setId(connection.addPlanet(TransformerToDTO.toPlanet(planet)));
        int planetID = planet.getId();
        for (Language language :
                planet.getLanguages()) {
            language.setId(connection.addLanguage(planetID, TransformerToDTO.toLanguage(language)));
        }
        for (Country country : planet.getCountries()) {
            country.setId(connection.addCountry(planetID, TransformerToDTO.toCountry(country)));
            int countryID = country.getId();
            for (Race race :
                    country.getRaces()) {
                race.setId(connection.addRace(countryID, TransformerToDTO.toRace(race)));
            }
        }
        connection.commit(); //How place it?

    }

    public void delete(Planet planet) throws DatabaseError {
        connection.deletePlanet(planet.getId());
        for (Language language : planet.getLanguages()) {
            connection.deleteLanguage(language.getId());
        }
        for (Country country : planet.getCountries()) {
            connection.deleteCountry(country.getId());
            for (Race race : country.getRaces()) {
                connection.deleteRace(race.getId());
            }
        }
        connection.commit(); //How place it?
    }

    public void update(Planet planet) throws DatabaseError {
        Planet original = this.get(planet.getId());
        connection.updatePlanet(TransformerToDTO.toPlanet(planet));
        //planet.setId(id);
        for (Language language : planet.getLanguages()) {
            if (language.getId() != -1) {
                connection.updateLanguage(TransformerToDTO.toLanguage(language));
            } else {
                language.setId(connection.addLanguage(planet.getId(), TransformerToDTO.toLanguage(language)));
            }
        }
        for (Language language :
                original.getLanguages()) {
            if (find(planet.getLanguages(), language.getId()) == null) {
                connection.deleteLanguage(language.getId());
            }
        }
        for (Country country : planet.getCountries()) {
            if (country.getId() != -1) {
                connection.updateCountry(TransformerToDTO.toCountry(country));
            } else {
                country.setId(connection.addCountry(planet.getId(), TransformerToDTO.toCountry(country)));
            }
            for (Race race : country.getRaces()) {
                if (race.getId() != -1) {
                    connection.updateRace(TransformerToDTO.toRace(race));
                } else {
                    race.setId(connection.addRace(country.getId(), TransformerToDTO.toRace(race)));
                }
            }
        }
        for (Country country : original.getCountries()) {
            if (find(planet.getCountries(), country.getId()) == null) {
                connection.deleteCountry(country.getId());
            }
            else{
                Country newCountry = find(planet.getCountries(), country.getId());
                if (newCountry != null) {
                    for (Race race : country.getRaces()) {
                        if (find(newCountry.getRaces(),race.getId()) == null) {
                            connection.deleteRace(race.getId());
                        }
                    }
                }
            }
        }

        connection.commit(); //How place it?
    }

    public static <T extends Comparable, P> T find(List<T> tList, P id) {
        int item = -1;
        int count = 0;
        for (T t : tList) {
            if (t.compare(id)) {
                item = count;
                break;
            }
            count++;
        }
        if (item != -1) {
            return tList.get(item);
        } else return null;
    }

    public static <T extends Comparable, P> int findIndex(List<T> tList, P id) {
        int item = -1;
        int count = 0;
        for (T t : tList) {
            if (t.compare(id)) {
                item = count;
                break;
            }
            count++;
        }
        return item;
    }

}
