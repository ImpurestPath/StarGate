package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface CountryDAO extends DAO<CountryDTO>{
    List<CountryDTO> getPlanetCountries(int id) throws ExceptionDAO;
    int add(int idPlanet, CountryDTO country) throws ExceptionDAO;
}
