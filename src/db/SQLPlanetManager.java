package db;

import java.sql.*;
import java.util.*;
import java.util.List;

public class SQLPlanetManager implements PlanetDAO {

    // Объект, в котором будет храниться соединение с БД
    Connection connection;

    public SQLPlanetManager(SQLConnection sqlConnection) throws ExceptionDAO {
        this.connection = sqlConnection.connection;
    }

    public List<PlanetDB> getAll() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<PlanetDB> planets = new ArrayList<>();


            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT idPlanet, name, temperature, pressure FROM Planet");
            /*PreparedStatement preparedStatementLanguage = connection.prepareStatement("SELECT Language.name,availableDictionary,TypeLanguage.name FROM Language LEFT JOIN TypeLanguage ON Language.idType = TypeLanguage.idType WHERE idPlanet = ?");
            PreparedStatement preparedStatementRace = connection.prepareStatement("SELECT Race.name,amount,Behavior.name from Race LEFT JOIN Behavior ON Race.idBehavior = Behavior.idBehavior WHERE idCountry = ?");
            PreparedStatement preparedStatementCountry = connection.prepareStatement("SELECT idCountry, Country.name,area FROM Country where idPlanet = ?");*/
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                /*List<LanguageDB> languages = new ArrayList<>();
                preparedStatementLanguage.setInt(1, resultSet.getInt(1));
                ResultSet languagesResult = preparedStatementLanguage.executeQuery();
                while (languagesResult.next()) {
                    languages.add(new LanguageDB(languagesResult.getString(1), languagesResult.getString(3), languagesResult.getInt(2)));
                }
                List<CountryDB> countries = new ArrayList<>();
                preparedStatementCountry.setInt(1, resultSet.getInt(1));
                ResultSet countriesResult = preparedStatementCountry.executeQuery();
                while (countriesResult.next()) {
                    List<RaceDB> races = new ArrayList<>();
                    preparedStatementRace.setInt(1, countriesResult.getInt(1));
                    ResultSet racesResult = preparedStatementRace.executeQuery();
                    while (racesResult.next()) {
                        races.add(new RaceDB(racesResult.getString(1), racesResult.getLong(2), racesResult.getString(3)));
                    }
                    countries.add(new CountryDB(countriesResult.getString(2), countriesResult.getLong(3), races));
                }*/
                planets.add(new PlanetDB(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getLong(4)));
            }
            // Возвращаем наш список
            return planets;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public int add(PlanetDB planet) throws ExceptionDAO {
        try (Statement statement = this.connection.createStatement()) {
            PreparedStatement preparedStatementPlanet = this.connection.prepareStatement(
                    "INSERT INTO Planet(`name`, `temperature`, `pressure`) " +
                            "VALUES(?, ?, ?)");
            preparedStatementPlanet.setString(1, planet.getName());
            preparedStatementPlanet.setInt(2, planet.getTemperature());
            preparedStatementPlanet.setLong(3, planet.getPressure());
            preparedStatementPlanet.execute();
            ResultSet lastId = statement.executeQuery("SELECT last_insert_rowid()");
            int id = lastId.getInt(1);
            planet.setId(id);
            //insertLanguages(planet, id);
            //insertCountries(planet, statement, id);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }

    /*private void insertCountries(PlanetDB planet, Statement statement, int id) throws SQLException {
        for (CountryDB country :
                planet.getCountries()) {
            PreparedStatement preparedStatementCountry = connection.prepareStatement("INSERT INTO" +
                    " Country('idPlanet','name','area') " + "VALUES (?,?,?)");
            preparedStatementCountry.setInt(1, id);
            preparedStatementCountry.setString(2, country.getName());
            preparedStatementCountry.setLong(3, country.getArea());
            preparedStatementCountry.execute();
            int countryID = statement.executeQuery("SELECT last_insert_rowid()").getInt(1);
            for (RaceDB race :
                    country.getRaces()) {
                PreparedStatement preparedStatementRace = connection.prepareStatement("INSERT INTO" +
                        " Race('idCountry','name','amount','idBehavior') " + "VALUES (?,?,?,?)");
                preparedStatementRace.setInt(1, countryID);
                preparedStatementRace.setString(2, race.getName());
                preparedStatementRace.setLong(3, race.getAmount());
                preparedStatementRace.setInt(4, race.getBoolBehavior() ? 2 : 1);
                preparedStatementRace.execute();
            }
        }
    }*/

    private void add(int id, PlanetDB planet) throws ExceptionDAO {

        try (Statement statement = this.connection.createStatement()) {
            PreparedStatement preparedStatementPlanet = this.connection.prepareStatement(
                    "INSERT INTO Planet('idPlanet',`name`, `temperature`, `pressure`) " +
                            "VALUES(?,?, ?, ?)");
            preparedStatementPlanet.setInt(1, id);
            preparedStatementPlanet.setString(2, planet.getName());
            preparedStatementPlanet.setInt(3, planet.getTemperature());
            preparedStatementPlanet.setLong(4, planet.getPressure());
            preparedStatementPlanet.execute();
            //insertLanguages(planet, id);
            //insertCountries(planet, statement, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }

    /*private void insertLanguages(PlanetDB planet, int id) throws SQLException {
        for (LanguageDB language :
                planet.getLanguages()) {
            PreparedStatement preparedStatementLanguage = connection.prepareStatement("INSERT INTO " +
                    "Language('idPlanet','name','availableDictionary','idType') VALUES (?,?,?,?)");
            preparedStatementLanguage.setInt(1, id);
            preparedStatementLanguage.setString(2, language.getName());
            preparedStatementLanguage.setInt(3, language.isAvailableDictionary() ? 1 : 0);
            preparedStatementLanguage.setInt(4, language.getType() == LanguageDB.Type.VOICE ?
                    1 : language.getType() == LanguageDB.Type.VISUAL ? 2 : 3);
            preparedStatementLanguage.execute();
        }
    }*/

    public void delete(int id) throws ExceptionDAO {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Planet WHERE idPlanet = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO(e);
        }
    }

    public void update(int idPlanet, PlanetDB planet) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Planet SET name = ?, temperature = ?, pressure = ? WHERE idPlanet = ?");
            preparedStatement.setString(1, planet.getName());
            preparedStatement.setInt(2, planet.getTemperature());
            preparedStatement.setLong(3,planet.getPressure());
            preparedStatement.setInt(4,idPlanet);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }


}
