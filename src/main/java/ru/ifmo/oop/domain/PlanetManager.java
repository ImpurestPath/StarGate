package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLConnection;
import ru.ifmo.oop.domain.Mappers.TransformerToDTO;
import ru.ifmo.oop.domain.Mappers.TransformerToEntity;

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
        for (Language language :
                planet.getLanguages()) {
            connection.deleteLanguage(language.getId());
        }
        for (Country country : planet.getCountries()) {
            connection.deleteCountry(country.getId());
            for (Race race :
                    country.getRaces()) {
                connection.deleteRace(race.getId());
            }
        }
        connection.commit(); //How place it?
    }

    public void update(int id, Planet planet) throws ExceptionDAO {
        connection.updatePlanet(id, TransformerToDTO.toPlanet(planet));
        planet.setId(id);
        for (Language language : planet.getLanguages()) {
            connection.updateLanguage(language.getId(), TransformerToDTO.toLanguage(language));
        }
        for (Country country : planet.getCountries()) {
            connection.updateCountry(country.getId(), TransformerToDTO.toCountry(country));
            for (Race race : country.getRaces()) {
                connection.updateRace(race.getId(), TransformerToDTO.toRace(race));
            }
        }
        connection.commit(); //How place it?
    }

    public void addManyPlanets() throws ExceptionDAO {
        for (int i = 0; i < 30000; i++) {
            add(new Planet("abc", 1, 1, new ArrayList<>(), new ArrayList<>()));
        }
    }
}
