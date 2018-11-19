package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface PlanetDAO {
    // Repository

    // Entity getById(int id);

    List<PlanetDTO> getAll() throws ExceptionDAO;

    PlanetDTO get(int idPlanet) throws ExceptionDAO;

    int add(PlanetDTO planetDTO) throws ExceptionDAO;

    void update(int idPlanet, PlanetDTO planet) throws ExceptionDAO;

    void delete(int idPlanet) throws ExceptionDAO;
}
