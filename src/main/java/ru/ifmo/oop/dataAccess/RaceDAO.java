package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.sql.DatabaseMetaData;
import java.util.List;

public interface RaceDAO extends DAO<RaceDTO> {
    List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO;
    int add(int idCountry, RaceDTO race) throws ExceptionDAO;
}
