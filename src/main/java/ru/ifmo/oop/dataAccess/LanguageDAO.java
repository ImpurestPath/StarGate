package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

import java.util.List;

public interface LanguageDAO extends DAO<LanguageDTO> {
    List<LanguageDTO> getPlanetLanguages(int id) throws ExceptionDAO;
    int add(int idPlanet, LanguageDTO languageDTO) throws ExceptionDAO;
}
