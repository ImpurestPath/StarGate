package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.PlanetDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface PlanetDAO extends DAO<PlanetDTO> {
    // Repository

    List<PlanetDTO> getAll() throws ExceptionDAO;

}
