package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.PlanetDB;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.util.List;

public interface PlanetDAO {
    // Repository

    // Entity getById(int id);

    List<PlanetDB> getAll() throws ExceptionDAO;

    int add(PlanetDB planetDB) throws ExceptionDAO;

    void update(int id, PlanetDB planet) throws ExceptionDAO;

    void delete(int id) throws ExceptionDAO;
}
