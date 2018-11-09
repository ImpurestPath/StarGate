package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.CountryDTO;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.util.List;

public interface CountryDAO {
    List<CountryDTO> getPlanetCountries(int id) throws ExceptionDAO;
    int insert(int idPlanet, CountryDTO country) throws ExceptionDAO;
    void delete(int idCountry) throws ExceptionDAO;
    void update(int idCountry, CountryDTO country) throws ExceptionDAO;
}
