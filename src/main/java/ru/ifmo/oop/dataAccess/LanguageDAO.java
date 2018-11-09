package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;

import java.util.List;

public interface LanguageDAO {
    List<LanguageDTO> getPlanetLanguages(int id) throws ExceptionDAO;
    int insert(int idPlanet, LanguageDTO languageDTO) throws ExceptionDAO;
    void delete(int idLanguage) throws ExceptionDAO;
    void update(int idLanguage, LanguageDTO language) throws ExceptionDAO;
}
