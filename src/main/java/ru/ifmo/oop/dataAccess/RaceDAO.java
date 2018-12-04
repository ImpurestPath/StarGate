package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

import java.util.List;

public interface RaceDAO extends DAO<RaceDTO> {
    List<RaceDTO> getCountryRaces(int idCountry) throws DatabaseError;
    int add(int idCountry, RaceDTO race) throws DatabaseError;
}
