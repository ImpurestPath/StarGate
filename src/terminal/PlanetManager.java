package terminal;

import db.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetManager {
    private final PlanetDAO planetDAO;
    private final LanguageDAO languageDAO;
    private final CountryDAO countryDAO;
    private final RaceDAO raceDAO;

    public PlanetManager(String file) throws ExceptionDAO {
        SQLConnection connection = SQLConnection.getInstance(file);
        this.planetDAO = new SQLPlanetManager(connection);
        this.languageDAO = new SQLLanguageManager(connection);
        this.countryDAO = new SQLCountryManager(connection);
        this.raceDAO = new SQLRaceManager(connection);
    }

    public List<Planet> getAll() throws ExceptionDAO {
        List<Planet> planets = new ArrayList<>();
        for (PlanetDB planetDB :
                planetDAO.getAll()) {
            List<CountryDB> countriesDB = countryDAO.getPlanetCountries(planetDB.getId());
            List<Country> countries = new ArrayList<>();
            for (CountryDB countryDB :
                    countriesDB) {
                List<RaceDB> racesDB = raceDAO.getCountryRaces(countryDB.getId());
                List<Race> races = new ArrayList<>();
                for (RaceDB raceDB:
                     racesDB) {
                    races.add(TransformerToEntity.toRace(raceDB));
                }
                countries.add(TransformerToEntity.toCountry(countryDB,races));
            }
            List<LanguageDB> languagesDB = languageDAO.getPlanetLanguages(planetDB.getId());
            List<Language> languages = new ArrayList<>();
            for (LanguageDB languageDB :
                    languagesDB) {
                languages.add(TransformerToEntity.toLanguage(languageDB));
            }
            planets.add(TransformerToEntity.toPlanet(planetDB,languages,countries)); //mappers work
        }
        return Collections.unmodifiableList(planets);
    }

    public void add(Planet planet) throws ExceptionDAO {
        planet.setId(planetDAO.add(TransformerToDTO.toPlanet(planet)));
        int planetID = planet.getId();
        for (Language language :
                planet.getLanguages()) {
            language.setId(languageDAO.insert(planetID,TransformerToDTO.toLanguage(language)));
        }
        for (Country country: planet.getCountries()) {
            country.setId(countryDAO.insert(planetID,TransformerToDTO.toCountry(country)));
            int countryID = country.getId();
            for (Race race :
                    country.getRaces()) {
                race.setId(raceDAO.insert(countryID, TransformerToDTO.toRace(race)));
            }
        }
    }

    public void delete(Planet planet) throws ExceptionDAO {
        planetDAO.delete(planet.getId());
        /*for (Language language :
                planet.getLanguages()) {
            languageDAO.delete(language.getId());
        }
        for (Country country: planet.getCountries()) {
            countryDAO.delete(country.getId());
            for (Race race :
                    country.getRaces()) {
                raceDAO.delete(race.getId());
            }
        }*/
    }

    public void update(int id, Planet planet) throws ExceptionDAO {
        planetDAO.update(id,TransformerToDTO.toPlanet(planet));
        planet.setId(id);
        for (Language language :
                planet.getLanguages()) {
            languageDAO.update(language.getId(),TransformerToDTO.toLanguage(language));
        }
        for (Country country: planet.getCountries()) {
            countryDAO.update(country.getId(),TransformerToDTO.toCountry(country));
            for (Race race :
                    country.getRaces()) {
                raceDAO.update(race.getId(),TransformerToDTO.toRace(race));
            }
        }
    }
}
