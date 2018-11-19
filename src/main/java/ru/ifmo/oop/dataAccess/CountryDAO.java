package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface CountryDAO {
    List<CountryDTO> getPlanetCountries(int id) throws ExceptionDAO;
    int insert(int idPlanet, CountryDTO country) throws ExceptionDAO;
    void delete(int idCountry) throws ExceptionDAO;
    void update(int idCountry, CountryDTO country) throws ExceptionDAO;
}
