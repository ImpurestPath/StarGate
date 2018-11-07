package ru.ifmo.oop.domain.Mappers;


import ru.ifmo.oop.db.DTO.*;
import ru.ifmo.oop.domain.*;

public class TransformerToDTO {
    public static PlanetDB toPlanet(Planet planet) {
        return new PlanetDB(planet.getName(), planet.getTemperature(), planet.getPressure());
    }

    public static LanguageDB toLanguage(Language language) {
        return new LanguageDB(language.getName(), language.getType(), language.isAvailableDictionary());
    }

    public static CountryDB toCountry(Country country) {
        return new CountryDB(country.getName(), country.getArea());
    }

    public static RaceDB toRace(Race race) {
        return new RaceDB(race.getName(), race.getAmount(), race.getBehavior());
    }

    public static UserDB toUser(User user) {
        return new UserDB(user.getName(), user.getPermission(), user.getIdCurrentPlanet());
    }
}
