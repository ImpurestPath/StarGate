package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.*;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

import java.util.List;

//TODO: delete all except commit
public interface ConnectionDAO {
    void commit() throws DatabaseError;
    List<PlanetDTO> getAllPlanets() throws DatabaseError;
    PlanetDTO getPlanet(int idPlanet) throws DatabaseError;
    List<LanguageDTO> getPlanetLanguages(int idPlanet) throws DatabaseError;
    List<CountryDTO> getPlanetCountries(int idPlanet) throws DatabaseError;
    List<RaceDTO> getCountryRaces(int idCountry) throws DatabaseError;
    UserDTO getUser(String name) throws DatabaseError;
    int addRace(int idCountry, RaceDTO race) throws DatabaseError;
    int addCountry(int idPlanet, CountryDTO country) throws DatabaseError;
    int addLanguage(int idPlanet, LanguageDTO languageDTO) throws DatabaseError;
    int addPlanet(PlanetDTO planetDTO) throws DatabaseError;
    int addUser(UserDTO userDTO) throws DatabaseError;
    void updateRace(RaceDTO race) throws DatabaseError;
    void updateCountry(CountryDTO country) throws DatabaseError;
    void updateLanguage(LanguageDTO language) throws DatabaseError;
    void updatePlanet(PlanetDTO planet) throws DatabaseError;
    void updateUser(UserDTO userDTO) throws DatabaseError;
    void deleteRace(int idRace) throws DatabaseError;
    void deleteCountry(int idCountry) throws DatabaseError;
    void deleteLanguage(int idLanguage) throws DatabaseError;
    void deletePlanet(int idPlanet) throws DatabaseError;
    void deleteUser(int idUser) throws DatabaseError;
}
