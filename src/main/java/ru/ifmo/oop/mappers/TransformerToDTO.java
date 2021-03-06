package ru.ifmo.oop.mappers;


import ru.ifmo.oop.dataAccess.DTO.*;
import ru.ifmo.oop.domain.*;

public class TransformerToDTO {
    public static PlanetDTO toPlanet(Planet planet) {
        return new PlanetDTO(planet.getId(), planet.getName(), planet.getTemperature(), planet.getPressure());
    }

    public static LanguageDTO toLanguage(Language language) {
        return new LanguageDTO(language.getId(),language.getName(), language.getType(), language.isAvailableDictionary(),language.getIdPlanet());
    }

    public static CountryDTO toCountry(Country country) {
        return new CountryDTO(country.getId(),country.getName(), country.getArea(),country.getIdPlanet());
    }

    public static RaceDTO toRace(Race race) {
        return new RaceDTO(race.getId(),race.getName(), race.getAmount(), race.getBehavior(),race.getIdCountry());
    }

    public static UserDTO toUser(User user) {
        return new UserDTO(user.getId(),user.getName(), user.getPermission(), user.getIdCurrentPlanet(),user.getPassword());
    }
}
