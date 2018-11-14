package ru.ifmo.oop.domain.mappers;


import ru.ifmo.oop.dataAccess.DTO.*;
import ru.ifmo.oop.domain.*;

import java.util.List;

public class TransformerToEntity {
    public static Planet toPlanet(PlanetDTO planetDTO, List<Language> languages, List<Country> countries) {
        return new Planet(planetDTO.getId(), planetDTO.getName(), planetDTO.getTemperature(), planetDTO.getPressure(), languages, countries);
    }

    public static Language toLanguage(LanguageDTO languageDTO) {
        return new Language(languageDTO.getId(), languageDTO.getName(), languageDTO.getType(), languageDTO.isAvailableDictionary());
    }

    public static Country toCountry(CountryDTO countryDTO, List<Race> races) {
        return new Country(countryDTO.getId(), countryDTO.getName(), countryDTO.getArea(), races);
    }

    public static Race toRace(RaceDTO raceDTO) {
        return new Race(raceDTO.getId(), raceDTO.getName(), raceDTO.getAmount(), raceDTO.getBehavior());
    }

    public static User toUser(UserDTO userDTO) {
        if (userDTO != null)
            return new User(userDTO.getName(), userDTO.getPermissions(), userDTO.getIdCurrentPlanet());
        else return null;
    }
}
