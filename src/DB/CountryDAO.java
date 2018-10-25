package DB;

import java.util.List;

public interface CountryDAO {
    List<CountryDB> getPlanetCountries(int id) throws ExceptionDAO;
    int insert(CountryDB country, int idPlanet) throws ExceptionDAO;
    void delete(int idCountry) throws ExceptionDAO;
    void update(int idCountry, CountryDB country) throws ExceptionDAO;
}
