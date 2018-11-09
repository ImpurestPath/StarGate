package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.RaceDTO;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.util.List;

public interface RaceDAO {
    List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO;
    int insert(int idCountry, RaceDTO race) throws ExceptionDAO;
    void delete(int idRace) throws ExceptionDAO;
    void update(int idRace, RaceDTO race) throws ExceptionDAO;
}
