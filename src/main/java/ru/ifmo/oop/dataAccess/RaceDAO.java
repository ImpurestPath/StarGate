package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface RaceDAO {
    List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO;
    int add(int idCountry, RaceDTO race) throws ExceptionDAO;
    void delete(int idRace) throws ExceptionDAO;
    void update(int idRace, RaceDTO race) throws ExceptionDAO;
}
