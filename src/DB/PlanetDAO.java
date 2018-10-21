package DB;

import java.util.List;

public interface PlanetDAO {
    List<PlanetDB> getAll() throws ExceptionDAO;

    int add(PlanetDB planetDB) throws ExceptionDAO;

    void update(int id, PlanetDB planet) throws ExceptionDAO;

    void delete(int id) throws ExceptionDAO;
}
