package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.CountryDTO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

import java.util.List;

public interface CountryDAO extends DAO<CountryDTO>{
    List<CountryDTO> getPlanetCountries(int id) throws DatabaseError;
    int add(int idPlanet, CountryDTO country) throws DatabaseError;
}
