package ru.ifmo.oop.db.SQL;

import ru.ifmo.oop.db.Exception.ExceptionDAO;
import ru.ifmo.oop.db.LanguageDAO;
import ru.ifmo.oop.db.DTO.LanguageDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLLanguageDAO implements LanguageDAO {
    private Connection connection;

    public SQLLanguageDAO(Connection connection) {
        this.connection = connection;
    }

    private int getIdType(LanguageDTO.Type type) {
        switch (type) {
            case VOICE:
                return 1;
            case VISUAL:
                return 2;
            case VIBRATION:
                return 3;
            default:
                return 1;
        }
    }

    @Override
    public List<LanguageDTO> getPlanetLanguages(int id) throws ExceptionDAO {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT idLanguage,Language.name,availableDictionary,TL.name " +
                                     "FROM Language " +
                                     "INNER JOIN TypeLanguage TL on Language.idType = TL.idType " +
                                     "WHERE idPlanet = ?")) {
            List<LanguageDTO> languages = new ArrayList<>();
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                languages.add(new LanguageDTO(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getInt(3)));
            }
            return languages;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public int insert(int idPlanet, LanguageDTO languageDTO) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementCountry = connection.prepareStatement("INSERT INTO" +
                    " Language('idPlanet','name','idType','availableDictionary') VALUES (?,?,?,?)");
            preparedStatementCountry.setInt(1, idPlanet);
            preparedStatementCountry.setString(2, languageDTO.getName());
            preparedStatementCountry.setInt(3, getIdType(languageDTO.getType()));
            preparedStatementCountry.setInt(4, languageDTO.isAvailableDictionary() ? 1 : 0);
            preparedStatementCountry.execute();
            int languageID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            languageDTO.setId(languageID);
            return languageID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public void delete(int idLanguage) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Language WHERE idLanguage = ?");
            preparedStatement.setInt(1, idLanguage);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public void update(int idLanguage, LanguageDTO language) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Language SET name = ?, idType = ?, availableDictionary = ? WHERE idLanguage = ?");
            preparedStatement.setString(1, language.getName());
            preparedStatement.setInt(2, getIdType(language.getType()));
            preparedStatement.setInt(3, language.isAvailableDictionary() ? 1 : 0);
            preparedStatement.setInt(4, idLanguage);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}