package db;

import java.util.List;

public interface LanguageDAO {
    List<LanguageDB> getPlanetLanguages(int id) throws ExceptionDAO;
    int insert(int idPlanet, LanguageDB languageDB) throws ExceptionDAO;
    void delete(int idLanguage) throws ExceptionDAO;
    void update(int idLanguage, LanguageDB language) throws ExceptionDAO;
}