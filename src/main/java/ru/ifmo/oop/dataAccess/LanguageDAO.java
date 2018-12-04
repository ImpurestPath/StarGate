package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

import java.util.List;

public interface LanguageDAO extends DAO<LanguageDTO> {
    List<LanguageDTO> getPlanetLanguages(int id) throws DatabaseError;
    int add(int idPlanet, LanguageDTO languageDTO) throws DatabaseError;
}
