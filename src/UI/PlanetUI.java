package UI;

import Terminal.Country;
import Terminal.Language;
import Terminal.Planet;
import Terminal.Race;

import java.util.List;

class PlanetUI {
    private final Planet planet;

    PlanetUI(Planet planet) {
        this.planet = planet;
    }

    Planet getPlanet() {
        return planet;
    }

    void view() {
        System.out.println("FromUI");
        System.out.printf("ID: %d\n", planet.getId());
        System.out.printf("Name: %s\n", planet.getName());
        System.out.printf("Behavior: %s\n", planet.getBehavior().toString());
        System.out.printf("Temperature: %d\n", planet.getTemperature());
        System.out.printf("Pressure: %d\n", planet.getPressure());
        if (planet.hasCountries()) {
            viewCountries(planet.getCountries());
        }
        if (planet.hasLanguages()) {
            viewLanguages(planet.getLanguages());
        }
        System.out.println();
    }

    static void viewCountries(List<Country> countries) {
        System.out.println("\tCountries:");
        for (Country country :
                countries) {
            viewCountry(country);
        }
    }

    static void viewLanguage(Language language) {
        System.out.printf("\t\tName: %s\n", language.getName());
        System.out.printf("\t\tType: %s\n", language.getType().toString());
        System.out.printf("\t\tAvailable dictionary: %s\n", language.isAvailableDictionary() ? "yes" : "no");
        System.out.println();
    }

    static void viewLanguages(List<Language> languages) {
        System.out.println("\tLanguages:");
        for (Language language :
                languages) {
            viewLanguage(language);
        }
    }

    private static void viewRace(Race race) {
        System.out.printf("\t\t\t\tName: %s\n", race.getName());
        System.out.printf("\t\t\t\tBehavior: %s\n", race.getBehavior().toString());
        System.out.printf("\t\t\t\tAmount: %d\n", race.getAmount());
        System.out.println();
    }

    static void viewRaces(List<Race> races) {
        System.out.println("\t\t\tRaces:");
        for (Race race :
                races) {
            viewRace(race);
        }
    }

    private static void viewCountry(Country country) {
        System.out.printf("\t\tName: %s\n", country.getName());
        System.out.printf("\t\tBehavior: %s\n", country.getBehavior().toString());
        System.out.printf("\t\tArea: %d\n", country.getArea());
        System.out.printf("\t\tAmountAlive: %d\n", country.getAmountAlive());
        if (country.hasRaces()) viewRaces(country.getRaces());
    }

}