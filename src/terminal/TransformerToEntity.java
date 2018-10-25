package terminal;

import db.*;
import terminal.Country;
import terminal.Language;
import terminal.Planet;
import terminal.Race;

import java.util.ArrayList;
import java.util.List;

public class TransformerToEntity {
    public static Planet toPlanet(PlanetDB planetDB, List<Language> languages, List<Country> countries){
        return new Planet(planetDB.getId(),planetDB.getName(),planetDB.getTemperature(),planetDB.getPressure(),languages,countries);
    }
    public static Language toLanguage(LanguageDB languageDB){
        return new Language(languageDB.getId(),languageDB.getName(),languageDB.getType(),languageDB.isAvailableDictionary());
    }
    public static Country toCountry(CountryDB countryDB, List<Race> races){
        return new Country(countryDB.getId(),countryDB.getName(),countryDB.getArea(),races);
    }
    public static Race toRace(RaceDB raceDB){
        return new Race(raceDB.getId(),raceDB.getName(),raceDB.getAmount(),raceDB.getBehavior());
    }
    public static User toUser(UserDB userDB){
        if (userDB != null)
        return new User(userDB.getName(),userDB.getIdCurrentPlanet());
        else return null;
    }
}
