package DB;

import java.util.List;

public interface LanguageDAO {
    List<LanguageDB> getPlanetLanguages(int id) throws ExceptionDAO;
    int insert(int idLanguage, LanguageDB race) throws ExceptionDAO;
    void delete(int idRace) throws ExceptionDAO;
    void update(int idRace, RaceDB race) throws ExceptionDAO;
}
