package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.CountryDB;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.util.List;

public interface CountryDAO {
    List<CountryDB> getPlanetCountries(int id) throws ExceptionDAO;
    int insert(int idPlanet, CountryDB country) throws ExceptionDAO;
    void delete(int idCountry) throws ExceptionDAO;
    void update(int idCountry, CountryDB country) throws ExceptionDAO;
}
