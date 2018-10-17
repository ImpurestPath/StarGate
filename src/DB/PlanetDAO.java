package DB;

import Terminal.Planet;

import java.util.List;

public interface PlanetDAO {
    List<PlanetDB> getAll() throws ExceptionDAO;
    int add(PlanetDB planetDB) throws ExceptionDAO;
    boolean update(int id, PlanetDB planet) throws ExceptionDAO;
    boolean delete(int id) throws ExceptionDAO;
}
