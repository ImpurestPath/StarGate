package db;

import terminal.*;


public class TransformerToDTO {
    public static PlanetDB toPlanet(Planet planet){
        return new PlanetDB(planet.getId(),planet.getName(),planet.getTemperature(),planet.getPressure());
    }
    public static LanguageDB toLanguage(Language language){
        return new LanguageDB(language.getId(),language.getName(),language.getType(),language.isAvailableDictionary());
    }
    public static CountryDB toCountry(Country country){
        return new CountryDB(country.getId(),country.getName(),country.getArea());
    }
    public static RaceDB toRace(Race race){
        return new RaceDB(race.getId(),race.getName(),race.getAmount(),race.getBehavior());
    }
    public static UserDB toUser(User user){
        return new UserDB(user.getName(),user.getIdCurrentPlanet());
    }
}
