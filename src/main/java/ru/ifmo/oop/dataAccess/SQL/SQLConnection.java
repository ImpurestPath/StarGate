package ru.ifmo.oop.dataAccess.SQL;

import org.sqlite.JDBC;
import ru.ifmo.oop.dataAccess.DTO.*;
import ru.ifmo.oop.dataAccess.UserDAO;
import ru.ifmo.oop.dataAccess.LanguageDAO;
import ru.ifmo.oop.dataAccess.ConnectionDAO;
import ru.ifmo.oop.dataAccess.CountryDAO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;
import ru.ifmo.oop.dataAccess.PlanetDAO;
import ru.ifmo.oop.dataAccess.RaceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SQLConnection implements ConnectionDAO {
    private static final String CON_STR = "jdbc:sqlite:";
    private Connection connection;
    private CountryDAO sqlCountryDAO;
    private PlanetDAO sqlPlanetDAO;
    private RaceDAO sqlRaceDAO;
    private UserDAO sqlUserDAO;
    private LanguageDAO sqlLanguageDAO;
    private static SQLConnection instance = null;
    public static synchronized SQLConnection getInstance(String filename) throws ExceptionDAO {
        if (instance == null)
            instance = new SQLConnection(filename);
        return instance;
    }
    private SQLConnection(String url) throws ExceptionDAO {
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
            throw new ExceptionDAO(e);
        }
    }

    public void commit() throws ExceptionDAO {
        try {
            connection.commit();
        }
        catch (SQLException e){
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public List<PlanetDTO> getAllPlanets() throws ExceptionDAO {
        return sqlPlanetDAO.getAll();
    }

    @Override
    public PlanetDTO getPlanet(int idPlanet) throws ExceptionDAO {
        return sqlPlanetDAO.get(idPlanet);
    }

    @Override
    public List<LanguageDTO> getPlanetLanguages(int idPlanet) throws ExceptionDAO {
        return sqlLanguageDAO.getPlanetLanguages(idPlanet);
    }

    @Override
    public List<CountryDTO> getPlanetCountries(int idPlanet) throws ExceptionDAO {
        return sqlCountryDAO.getPlanetCountries(idPlanet);
    }

    @Override
    public List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO {
        return sqlRaceDAO.getCountryRaces(idCountry);
    }

    @Override
    public UserDTO getUser(String name) throws ExceptionDAO {
        return sqlUserDAO.get(name);
    }

    @Override
    public int addRace(int idCountry, RaceDTO race) throws ExceptionDAO {
        return sqlRaceDAO.add(idCountry,race);
    }

    @Override
    public int addCountry(int idPlanet, CountryDTO country) throws ExceptionDAO {
        return sqlCountryDAO.insert(idPlanet, country);
    }

    @Override
    public int addLanguage(int idPlanet, LanguageDTO languageDTO) throws ExceptionDAO {
        return sqlLanguageDAO.insert(idPlanet, languageDTO);
    }

    @Override
    public int addPlanet(PlanetDTO planetDTO) throws ExceptionDAO {
        return sqlPlanetDAO.add(planetDTO);
    }

    @Override
    public int addUser(UserDTO userDTO) throws ExceptionDAO {
        return sqlUserDAO.add(userDTO);
    }

    @Override
    public void updateRace(int idRace, RaceDTO race) throws ExceptionDAO {
        sqlRaceDAO.update(idRace, race);
    }

    @Override
    public void updateCountry(int idCountry, CountryDTO country) throws ExceptionDAO {
        sqlCountryDAO.update(idCountry, country);
    }

    @Override
    public void updateLanguage(int idLanguage, LanguageDTO language) throws ExceptionDAO {
        sqlLanguageDAO.update(idLanguage, language);
    }

    @Override
    public void updatePlanet(int idPlanet, PlanetDTO planet) throws ExceptionDAO {
        sqlPlanetDAO.update(idPlanet, planet);
    }

    @Override
    public void updateUser(int idUser, UserDTO userDTO) throws ExceptionDAO {
        sqlUserDAO.update(idUser, userDTO);
    }

    @Override
    public void deleteRace(int idRace) throws ExceptionDAO {
        sqlRaceDAO.delete(idRace);
    }

    @Override
    public void deleteCountry(int idCountry) throws ExceptionDAO {
        sqlCountryDAO.delete(idCountry);
    }

    @Override
    public void deleteLanguage(int idLanguage) throws ExceptionDAO {
        sqlLanguageDAO.delete(idLanguage);
    }

    @Override
    public void deletePlanet(int idPlanet) throws ExceptionDAO {
        sqlPlanetDAO.delete(idPlanet);
    }

    @Override
    public void deleteUser(int idUser) throws ExceptionDAO {
        sqlUserDAO.delete(idUser);
    }
}
