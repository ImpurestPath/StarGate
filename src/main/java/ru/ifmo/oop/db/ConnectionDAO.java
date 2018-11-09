package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.*;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.util.List;

//TODO: ask about facade
public interface ConnectionDAO {
    void commit() throws ExceptionDAO;
    List<PlanetDTO> getAllPlanets() throws ExceptionDAO;
    PlanetDTO getPlanet(int idPlanet) throws ExceptionDAO;
    List<LanguageDTO> getPlanetLanguages(int idPlanet) throws ExceptionDAO;
    List<CountryDTO> getPlanetCountries(int idPlanet) throws ExceptionDAO;
    List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO;
    UserDTO getUser(String name) throws ExceptionDAO;
    int addRace(int idCountry, RaceDTO race) throws ExceptionDAO;
    int addCountry(int idPlanet, CountryDTO country) throws ExceptionDAO;
    int addLanguage(int idPlanet, LanguageDTO languageDTO) throws ExceptionDAO;
    int addPlanet(PlanetDTO planetDTO) throws ExceptionDAO;
    int addUser(UserDTO userDTO) throws ExceptionDAO;
    void updateRace(int idRace, RaceDTO race) throws ExceptionDAO;
    void updateCountry(int idCountry, CountryDTO country) throws ExceptionDAO;
    void updateLanguage(int idLanguage, LanguageDTO language) throws ExceptionDAO;
    void updatePlanet(int idPlanet, PlanetDTO planet) throws ExceptionDAO;
    void updateUser(int idUser, UserDTO userDTO) throws ExceptionDAO;
    void deleteRace(int idRace) throws ExceptionDAO;
    void deleteCountry(int idCountry) throws ExceptionDAO;
    void deleteLanguage(int idLanguage) throws ExceptionDAO;
    void deletePlanet(int idPlanet) throws ExceptionDAO;
    void deleteUser(int idUser) throws ExceptionDAO;
}
