package DB;

import java.util.List;

public interface RaceDAO {
    List<RaceDB> getCountryRaces(int idCountry) throws ExceptionDAO;
    int insert(int idRace, RaceDB race) throws ExceptionDAO;
    void delete(int idRace) throws ExceptionDAO;
    void update(int idRace, RaceDB race) throws ExceptionDAO;
}
