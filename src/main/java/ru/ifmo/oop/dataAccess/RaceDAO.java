package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.RaceDTO;
import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;

import java.util.List;

public interface RaceDAO {
    List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO;
    int insert(int idCountry, RaceDTO race) throws ExceptionDAO;
    void delete(int idRace) throws ExceptionDAO;
    void update(int idRace, RaceDTO race) throws ExceptionDAO;
}
