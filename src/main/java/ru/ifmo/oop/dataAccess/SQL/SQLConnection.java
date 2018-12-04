package ru.ifmo.oop.dataAccess.SQL;

import org.sqlite.JDBC;
import ru.ifmo.oop.dataAccess.DTO.*;
import ru.ifmo.oop.dataAccess.UserDAO;
import ru.ifmo.oop.dataAccess.LanguageDAO;
import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.CountryDAO;
import ru.ifmo.oop.dataAccess.exception.ConnectionError;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.dataAccess.PlanetDAO;
import ru.ifmo.oop.dataAccess.RaceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SQLConnection implements ConnectionDAO {
    private static final String CON_STR = "jdbc:sqlite:";
    private static SQLConnection instance = null;
    private Connection connection;
    private CountryDAO sqlCountryDAO;
    private PlanetDAO sqlPlanetDAO;
    private RaceDAO sqlRaceDAO;
    private UserDAO sqlUserDAO;
    private LanguageDAO sqlLanguageDAO;

    private SQLConnection(String url) throws ConnectionError {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR + url);
            this.sqlPlanetDAO = new SQLPlanetDAO(connection);
            this.sqlCountryDAO = new SQLCountryDAO(connection);
            this.sqlLanguageDAO = new SQLLanguageDAO(connection);
            this.sqlRaceDAO = new SQLRaceDAO(connection);
            this.sqlUserDAO = new SQLUserDAO(connection);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionError(e);
        }
    }

    public static synchronized SQLConnection getInstance(String filename) throws ConnectionError {
        if (instance == null)
            instance = new SQLConnection(filename);
        return instance;
    }

    public void commit() throws DatabaseError {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Override
    public List<PlanetDTO> getAllPlanets() throws DatabaseError {
        return sqlPlanetDAO.getAll();
    }

    @Override
    public PlanetDTO getPlanet(int idPlanet) throws DatabaseError {
        return sqlPlanetDAO.get(idPlanet);
    }

    @Override
    public List<LanguageDTO> getPlanetLanguages(int idPlanet) throws DatabaseError {
        return sqlLanguageDAO.getPlanetLanguages(idPlanet);
    }

    @Override
    public List<CountryDTO> getPlanetCountries(int idPlanet) throws DatabaseError {
        return sqlCountryDAO.getPlanetCountries(idPlanet);
    }

    @Override
    public List<RaceDTO> getCountryRaces(int idCountry) throws DatabaseError {
        return sqlRaceDAO.getCountryRaces(idCountry);
    }

    @Override
    public UserDTO getUser(String name) throws DatabaseError {
        return sqlUserDAO.get(name);
    }

    @Override
    public int addRace(int idCountry, RaceDTO race) throws DatabaseError {
        return sqlRaceDAO.add(idCountry, race);
    }

    @Override
    public int addCountry(int idPlanet, CountryDTO country) throws DatabaseError {
        return sqlCountryDAO.add(idPlanet, country);
    }

    @Override
    public int addLanguage(int idPlanet, LanguageDTO languageDTO) throws DatabaseError {
        return sqlLanguageDAO.add(idPlanet, languageDTO);
    }

    @Override
    public int addPlanet(PlanetDTO planetDTO) throws DatabaseError {
        return sqlPlanetDAO.add(planetDTO);
    }

    @Override
    public int addUser(UserDTO userDTO) throws DatabaseError {
        return sqlUserDAO.add(userDTO);
    }

    @Override
    public void updateRace(RaceDTO race) throws DatabaseError {
        sqlRaceDAO.update(race);
    }

    @Override
    public void updateCountry(CountryDTO country) throws DatabaseError {
        sqlCountryDAO.update(country);
    }

    @Override
    public void updateLanguage(LanguageDTO language) throws DatabaseError {
        sqlLanguageDAO.update(language);
    }

    @Override
    public void updatePlanet(PlanetDTO planet) throws DatabaseError {
        sqlPlanetDAO.update(planet);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws DatabaseError {
        sqlUserDAO.update(userDTO);
    }

    @Override
    public void deleteRace(int idRace) throws DatabaseError {
        sqlRaceDAO.delete(idRace);
    }

    @Override
    public void deleteCountry(int idCountry) throws DatabaseError {
        sqlCountryDAO.delete(idCountry);
    }

    @Override
    public void deleteLanguage(int idLanguage) throws DatabaseError {
        sqlLanguageDAO.delete(idLanguage);
    }

    @Override
    public void deletePlanet(int idPlanet) throws DatabaseError {
        sqlPlanetDAO.delete(idPlanet);
    }

    @Override
    public void deleteUser(int idUser) throws DatabaseError {
        sqlUserDAO.delete(idUser);
    }
}
