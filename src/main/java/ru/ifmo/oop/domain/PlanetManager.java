package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLConnection;
import ru.ifmo.oop.domain.mappers.TransformerToDTO;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    private final ConnectionDAO connection;

    public PlanetManager(String filename) throws ExceptionDAO {
        this.connection = SQLConnection.getInstance(filename);
    }

    public List<Planet> getAll() throws ExceptionDAO {
        List<Planet> planets = new ArrayList<>();
        for (PlanetDTO planetDTO :
                connection.getAllPlanets()) {
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
            planets.add(TransformerToEntity.toPlanet(planetDTO, languages, countries)); //mappers work
        }
        return Collections.unmodifiableList(planets);
    }

    public Planet get(int idPlanet) throws ExceptionDAO {
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

    public void add(Planet planet) throws ExceptionDAO {
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

    public void delete(Planet planet) throws ExceptionDAO {
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

    public void update(int id, Planet planet) throws ExceptionDAO {
        Planet original = this.get(id);
        connection.updatePlanet(id, TransformerToDTO.toPlanet(planet));
        planet.setId(id);
        for (Language language : planet.getLanguages()) {
            if (language.getId() != -1) {
                connection.updateLanguage(language.getId(), TransformerToDTO.toLanguage(language));
            } else {
                language.setId(connection.addLanguage(id, TransformerToDTO.toLanguage(language)));
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
                connection.updateCountry(country.getId(), TransformerToDTO.toCountry(country));
            } else {
                country.setId(connection.addCountry(id, TransformerToDTO.toCountry(country)));
                connection.commit();
                //TODO solve problem when country haven't id before adding races
            }
            for (Race race : country.getRaces()) {
                if (race.getId() != -1) {
                    connection.updateRace(race.getId(), TransformerToDTO.toRace(race));
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
