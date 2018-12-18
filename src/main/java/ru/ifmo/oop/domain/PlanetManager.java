package ru.ifmo.oop.domain;


import ru.ifmo.oop.dataAccess.*;
import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.SQL.SQLCountryDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLLanguageDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLPlanetDAO;
import ru.ifmo.oop.dataAccess.SQL.SQLRaceDAO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.mappers.TransformerToDTO;
import ru.ifmo.oop.mappers.TransformerToEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager implements IPlanetManager {
    private final ConnectionDAO connection;
    private final CountryDAO countryDAO;
    private final LanguageDAO languageDAO;
    private final PlanetDAO planetDAO;
    private final RaceDAO raceDAO;

    public PlanetManager(ConnectionDAO<Connection> connection) {
        this.connection = connection;
        this.countryDAO = new SQLCountryDAO(connection.getConnection());
        this.languageDAO = new SQLLanguageDAO(connection.getConnection());
        this.planetDAO = new SQLPlanetDAO(connection.getConnection());
        this.raceDAO = new SQLRaceDAO(connection.getConnection());
    }

    private Planet buildPlanet(int idPlanet) throws DatabaseError {
        PlanetDTO planetDTO = planetDAO.get(idPlanet);
        List<CountryDTO> countriesDB = countryDAO.getPlanetCountries(planetDTO.getId());
        List<Country> countries = new ArrayList<>();
        for (CountryDTO countryDTO :
                countriesDB) {
            List<RaceDTO> racesDB = raceDAO.getCountryRaces(countryDTO.getId());
            List<Race> races = new ArrayList<>();
            for (RaceDTO raceDTO :
                    racesDB) {
                races.add(TransformerToEntity.toRace(raceDTO));
            }
            countries.add(TransformerToEntity.toCountry(countryDTO, races));
        }
        List<LanguageDTO> languagesDB = languageDAO.getPlanetLanguages(planetDTO.getId());
        List<Language> languages = new ArrayList<>();
        for (LanguageDTO languageDTO :
                languagesDB) {
            languages.add(TransformerToEntity.toLanguage(languageDTO));
        }
        return TransformerToEntity.toPlanet(planetDTO, languages, countries);
    }

    public Observable<List<Planet>> getAll() {
        Observable<List<Planet>> observable = new Observable<>() {
            List<Listener> listeners = new ArrayList<>();

            @Override
            public void addListener(Listener listener) {
                listeners.add(listener);
            }

            @Override
            public void deleteListener(Listener listener) {
                listeners.remove(listener);
            }

            @Override
            public void notifyListeners(double value) {
                for (Listener listener : listeners) {
                    listener.handle(value);
                }
            }

            @Override
            public void finishListeners() {
                for (Listener listener : listeners) {
                    listener.onFinish();
                }
            }

            @Override
            public List<Planet> call() throws DatabaseError {
                List<Planet> planets = new ArrayList<>();
                List<Integer> id = planetDAO.getAllId();
                for (int i :
                        id) {
                    planets.add(buildPlanet(i));
                    notifyListeners((double) planets.size() / id.size());
                }
                finishListeners();
                return Collections.unmodifiableList(planets);
            }
        };
        return observable;
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

    @Override
    public Planet get(int idPlanet) throws DatabaseError {
        return buildPlanet(idPlanet);
    }

    @Override
    public void add(Planet planet) throws DatabaseError {
        planet.setId(planetDAO.add(TransformerToDTO.toPlanet(planet)));
        int planetID = planet.getId();
        for (Language language :
                planet.getLanguages()) {
            language.setId(languageDAO.add(planetID, TransformerToDTO.toLanguage(language)));
        }
        for (Country country : planet.getCountries()) {
            country.setId(countryDAO.add(planetID, TransformerToDTO.toCountry(country)));
            int countryID = country.getId();
            for (Race race :
                    country.getRaces()) {
                race.setId(raceDAO.add(countryID, TransformerToDTO.toRace(race)));
            }
        }
        connection.commit(); //How place it?

    }

    @Override
    public void delete(Planet planet) throws DatabaseError {
        planetDAO.delete(planet.getId());
        for (Language language : planet.getLanguages()) {
            languageDAO.delete(language.getId());
        }
        for (Country country : planet.getCountries()) {
            countryDAO.delete(country.getId());
            for (Race race : country.getRaces()) {
                raceDAO.delete(race.getId());
            }
        }
        connection.commit(); //How place it?
    }

    @Override
    public void update(Planet planet) throws DatabaseError {
        Planet original = this.get(planet.getId());
        planetDAO.update(TransformerToDTO.toPlanet(planet));
        //planet.setId(id);
        for (Language language : planet.getLanguages()) {
            if (language.getId() != -1) {
                languageDAO.update(TransformerToDTO.toLanguage(language));
            } else {
                language.setId(languageDAO.add(planet.getId(), TransformerToDTO.toLanguage(language)));
            }
        }
        for (Language language :
                original.getLanguages()) {
            if (find(planet.getLanguages(), language.getId()) == null) {
                languageDAO.delete(language.getId());
            }
        }
        for (Country country : planet.getCountries()) {
            if (country.getId() != -1) {
                countryDAO.update(TransformerToDTO.toCountry(country));
            } else {
                country.setId(countryDAO.add(planet.getId(), TransformerToDTO.toCountry(country)));
            }
            for (Race race : country.getRaces()) {
                if (race.getId() != -1) {
                    raceDAO.update(TransformerToDTO.toRace(race));
                } else {
                    race.setId(raceDAO.add(country.getId(), TransformerToDTO.toRace(race)));
                }
            }
        }
        for (Country country : original.getCountries()) {
            if (find(planet.getCountries(), country.getId()) == null) {
                countryDAO.delete(country.getId());
            } else {
                Country newCountry = find(planet.getCountries(), country.getId());
                if (newCountry != null) {
                    for (Race race : country.getRaces()) {
                        if (find(newCountry.getRaces(), race.getId()) == null) {
                            raceDAO.delete(race.getId());
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
