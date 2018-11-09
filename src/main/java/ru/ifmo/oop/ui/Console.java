package ru.ifmo.oop.ui;

import ru.ifmo.oop.db.DTO.LanguageDB;
import ru.ifmo.oop.db.Exception.ExceptionDAO;
import ru.ifmo.oop.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final Scanner scanner;
    private final PlanetManager planetManager;
    private final UserManager userManager;
    private User user;
    private final int idGatePlanet;
    private List<PlanetUI> planetUIList;




    public Console(PlanetManager planetManager, UserManager userManager, int idGatePlanet){
        this.scanner = new Scanner(System.in);
        this.planetManager = planetManager;
        this.userManager = userManager;
        this.idGatePlanet = idGatePlanet;
        planetUIList = new ArrayList<>();
        //planetManager.addManyPlanets();
    }

    public class LoadPlanets implements Runnable { //Move to constructor?
        public void run() {
            try {
                for (Planet planet :
                        planetManager.getAll()) {
                    planetUIList.add(new PlanetUI(planet));
                    Thread.sleep(1);
                }
            } catch (ExceptionDAO e) {
                System.out.println("Failure of loading");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class LoadingScreen implements Runnable {
        private int numberOfPoints;

        public void run() {
            numberOfPoints = 0;
            while (true) {
                if (Thread.interrupted()) return;
                System.out.print("Loading");
                numberOfPoints = (numberOfPoints + 1) % 5;
                for (int i = 0; i < numberOfPoints; i++) {
                    System.out.print(".");
                }
                System.out.println();
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    //System.out.println("Thread exception");
                    return;
                }
            }
        }
    }

    private void changeUser() throws ExceptionDAO {
        //switchCurrentUser
        System.out.println("Login:");
        String login = scanner.next();
        User tempUser = userManager.get(login);
        if (tempUser != null)
            this.user = tempUser; //Do when not found and registration
        else {
            System.out.println("No such user, register please");
            System.out.println("Login:");
            User newUser = new User(scanner.next(), "", idGatePlanet);
            userManager.add(newUser); //Maybe constant
            this.user = newUser;
        }
    }

    private <T extends Searchable, P> int findT(List<T> tList, P id) {
        int count = 0;
        for (T t :
                tList) {
            if (t.merge(id)) return count;
            count++;
        }
        return -1;
    }

    private <T extends Searchable> void deleteT(List<T> tList, String name) {
        //Maybe print name
        int i = findT(tList, name);
        if (i != -1) tList.remove(i);
        else System.out.println("No such object");
    }

    private void viewPlanets() {
        int page = 1;
        System.out.printf("Available pages: %d\n", (planetUIList.size() + 4) / 5);
        while (true) {
            System.out.printf("Page %d\n", page);
            for (int i = 5 * (page - 1) + 1; i <= 5 * page && i <= planetUIList.size(); i++) {
                int count = 1;
                for (PlanetUI planet :
                        planetUIList) {
                    if (count > i) break;
                    else if (i == count) planet.view();
                    count++;
                }
            }
            System.out.println("Choose page: (0 for exit)");
            page = scanner.nextInt();
            if (page == 0) return;
            if (page > (planetUIList.size() + 4) / 5 || page < 1) {
                System.out.println("Wrong page!");
                page = 1;
            }
        }
    }

    private Race addRace() {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Amount: ");
        long amount = scanner.nextLong();
        System.out.println("Behavior: ");
        System.out.println("1.Angry");
        System.out.println("2.Neutral");
        boolean behavior;
        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    behavior = true;
                    break;
                case 2:
                    behavior = false;
                    break;
                default:
                    continue;
            }
            break;
        }
        System.out.println("Successful");
        return new Race(name, amount, behavior);
    }

    private Country addCountry() {

        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Area: ");
        long area = scanner.nextLong();
        List<Race> races = new ArrayList<>();
        while (true) {
            System.out.println("Choose: ");
            System.out.println("0.Finish");
            System.out.println("1.Add race");
            switch (scanner.nextInt()) {
                case 0:
                    System.out.println("Successful");
                    return new Country(name, area, races);
                case 1:
                    races.add(addRace());
                    break;
                default:
                    break;
            }
        }
    }

    private Language addLanguage() {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Type: ");
        System.out.println("1.Voice");
        System.out.println("2.Visual");
        System.out.println("3.Vibration");
        LanguageDB.Type type;
        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    type = LanguageDB.Type.VOICE;
                    break;
                case 2:
                    type = LanguageDB.Type.VISUAL;
                    break;
                case 3:
                    type = LanguageDB.Type.VIBRATION;
                    break;
                default:
                    continue;
            }
            break;
        }
        System.out.println("Dictionary:");
        System.out.println("1. Available");
        System.out.println("2. Unavailable");
        boolean available;
        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    available = true;
                    break;
                case 2:
                    available = false;
                    break;
                default:
                    continue;
            }
            break;
        }
        System.out.println("Successful");
        return new Language(name, type, available);
    }

    private void addPlanet() throws ExceptionDAO {
        Planet newPlanet = buildPlanet();
        planetManager.add(newPlanet);
        planetUIList.add(new PlanetUI(newPlanet));
        System.out.println("Successful");
    }

    private Planet buildPlanet() {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Temperature: ");
        int temperature = scanner.nextInt();
        System.out.println("Pressure: ");
        long pressure = scanner.nextLong();
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        while (true) {
            System.out.println("0.Finish");
            System.out.println("1.Add language");
            System.out.println("2.Add country");
            switch (scanner.nextInt()) {
                case 0:
                    return new Planet(name, temperature, pressure, languages, countries);
                case 1:
                    languages.add(addLanguage());
                    break;
                case 2:
                    countries.add(addCountry());
                    break;
                default:
                    break;
            }
        }
    }

    private void deletePlanet() throws ExceptionDAO {
        System.out.println("Enter id of currentPlanet (0 to see available planets, -1 for exit)");
        int choose = scanner.nextInt();
        if (choose == 0) {
            viewPlanets();
            deletePlanet();
        } else if (choose > 0) {
            if (findT(planetUIList, choose) != -1) {
                int count = findT(this.planetUIList, choose);
                planetUIList.get(count).view();
                System.out.println("Are you sure?(y/n)");
                switch (scanner.next().charAt(0)) {
                    case 'y':
                    case 'Y':
                        planetManager.delete(planetUIList.get(count).getPlanet());
                        System.out.println("Successful");
                        planetUIList.remove(count);
                        break;
                    default:
                        System.out.println("Cancelled");
                        break;
                }
            } else {
                System.out.println("No currentPlanet with this id");
                deletePlanet();
            }
        }
    }

    private void updateRace(List<Race> races) {
        PlanetUI.viewRaces(races);
        System.out.println("Type name of race");
        int i = findT(races, scanner.next());
        if (i == -1) {
            System.out.println("No such race");
            return;
        }
        Race oldRace = races.get(i);
        System.out.println("Name:(0 for origin)");
        String name = scanner.next();
        System.out.println("Amount:(0 for origin) ");
        long amount = scanner.nextLong();
        System.out.println("Behavior: ");
        System.out.println("0.Origin");
        System.out.println("1.Angry");
        System.out.println("2.Neutral");
        boolean behavior;
        while (true) {
            switch (scanner.nextInt()) {
                case 0:
                    behavior = oldRace.getBoolBehavior();
                    break;
                case 1:
                    behavior = true;
                    break;
                case 2:
                    behavior = false;
                    break;
                default:
                    continue;
            }
            break;
        }
        races.set(i, new Race(name.equals("0") ? oldRace.getName() : name,
                amount == 0 ? oldRace.getAmount() : amount,
                behavior));

    }

    private void updateCountry(List<Country> countries) {
        PlanetUI.viewCountries(countries);
        System.out.println("Type name of country");
        int i = findT(countries, scanner.next());
        if (i == -1) {
            System.out.println("No such country");
            return;
        }
        Country oldCountry = countries.get(i);
        System.out.println("Name:(0 for origin)");
        String name = scanner.next();
        System.out.println("Area:(0 for origin) ");
        long area = scanner.nextLong();
        List<Race> races = new ArrayList<>(oldCountry.getRaces());
        while (true) {
            System.out.println("Choose: ");
            System.out.println("0.Finish");
            System.out.println("1.Add race");
            System.out.println("2.Update race");
            System.out.println("3.Delete race");
            switch (scanner.nextInt()) {
                case 0:
                    System.out.println("Successful");
                    countries.set(i, new Country(name.equals("0") ? oldCountry.getName() : name,
                            area == 0 ? oldCountry.getArea() : area,
                            races));
                    return;
                case 1:
                    races.add(addRace());
                    break;
                case 2:
                    updateRace(races);
                    break;
                case 3:
                    PlanetUI.viewRaces(races);
                    System.out.println("Type name of race:");
                    deleteT(races, scanner.next());
                    break;
                default:
                    break;
            }
        }
    }

    private void updateLanguage(List<Language> languages) {
        PlanetUI.viewLanguages(languages);
        System.out.println("Name of language:");
        int i = findT(languages, scanner.next());
        if (i == -1) {
            System.out.println("No currentPlanet with this name");
            return;
        }
        Language oldLanguage = languages.get(i);
        PlanetUI.viewLanguage(oldLanguage);
        System.out.println("Name:(0 for origin)");
        String name = scanner.next();
        System.out.println("Type: ");
        System.out.println("0.Origin");
        System.out.println("1.Voice");
        System.out.println("2.Visual");
        System.out.println("3.Vibration");
        LanguageDB.Type type;
        while (true) {
            switch (scanner.nextInt()) {
                case 0:
                    type = oldLanguage.getType();
                    break;
                case 1:
                    type = LanguageDB.Type.VOICE;
                    break;
                case 2:
                    type = LanguageDB.Type.VISUAL;
                    break;
                case 3:
                    type = LanguageDB.Type.VIBRATION;
                    break;
                default:
                    continue;
            }
            break;
        }
        System.out.println("Dictionary:");
        System.out.println("0. Origin");
        System.out.println("1. Available");
        System.out.println("2. Unavailable");
        boolean available;
        while (true) {
            switch (scanner.nextInt()) {
                case 0:
                    available = oldLanguage.isAvailableDictionary();
                    break;
                case 1:
                    available = true;
                    break;
                case 2:
                    available = false;
                    break;
                default:
                    continue;
            }
            break;
        }
        System.out.println("Successful");
        languages.set(i, new Language(name.equals("0") ? oldLanguage.merge() : name, type, available));
    }

    private void updatePlanet() throws ExceptionDAO {
        System.out.println("Enter id of currentPlanet (0 to see available planets, -1 for exit)");
        int choose = scanner.nextInt();
        if (choose == 0) {
            viewPlanets();
            updatePlanet();
        } else if (choose > 0) {
            int UIitem = findT(planetUIList, choose);
            if (UIitem != -1) {
                Planet oldPlanet = planetUIList.get(UIitem).getPlanet();
                planetUIList.get(UIitem).view();
                System.out.println("Name:(0 for origin) ");
                String name = scanner.next();
                System.out.println("Temperature:(0 for origin) ");
                int temperature = scanner.nextInt();
                System.out.println("Pressure:(0 for origin) ");
                long pressure = scanner.nextLong();
                List<Language> languages = new ArrayList<>(oldPlanet.getLanguages());
                List<Country> countries = new ArrayList<>(oldPlanet.getCountries());
                while (true) {
                    System.out.println("0.Finish");
                    System.out.println("1.Add language");
                    System.out.println("2.Add country");
                    System.out.println("3.Delete language");
                    System.out.println("4.Delete country");
                    System.out.println("5.Update language");
                    System.out.println("6.Update country");
                    switch (scanner.nextInt()) {
                        case 0:
                            Planet newPlanet = new Planet(name.equals("0") ? oldPlanet.getName() : name,
                                    temperature == 0 ? oldPlanet.getTemperature() : temperature,
                                    pressure == 0 ? oldPlanet.getPressure() : pressure,
                                    languages,
                                    countries);
                            planetManager.update(choose, newPlanet);
                            planetUIList.set(UIitem, new PlanetUI(newPlanet));
                            return;
                        case 1:
                            languages.add(addLanguage());
                            break;
                        case 2:
                            countries.add(addCountry());
                            break;
                        case 3:
                            PlanetUI.viewLanguages(languages);
                            System.out.println("Type name of language");
                            deleteT(languages, scanner.next());
                            break;
                        case 4:
                            PlanetUI.viewCountries(countries);
                            System.out.println("Type name of country");
                            deleteT(countries, scanner.next());
                            break;
                        case 5:
                            updateLanguage(languages);
                            break;
                        case 6:
                            updateCountry(countries);
                            break;
                        default:
                            break;
                    }
                }
            } else {
                System.out.println("No planet with this id");
                updatePlanet();
            }
        }
    }

    private void moveUser() {
        System.out.println("Enter id of planet (0 to see available planets, -1 for exit)");
        int choose = scanner.nextInt();
        if (choose == 0) {
            viewPlanets();
            moveUser();
        } else if (choose > 0) {
            int UIitem = findT(planetUIList, choose);
            if (UIitem != -1) {
                PlanetUI userPlanetUI = planetUIList.get(UIitem);
                userPlanetUI.view();
                if (userPlanetUI.getPlanet().isAngry()) {
                    System.out.println("Angry planet. Are you sure?(y/n)");
                    switch (scanner.next().charAt(0)) {
                        case 'y':
                        case 'Y':
                            StarGate.moveUser(userPlanetUI.getPlanet().getId(), user);
                            System.out.println("Successful");
                            break;
                        default:
                            System.out.println("Good choose");
                    }
                } else {
                    StarGate.moveUser(userPlanetUI.getPlanet().getId(), user);
                }
            } else {
                System.out.println("No planet with this id");
                moveUser();
            }
        }
    }

    private void addUser() throws ExceptionDAO {
        System.out.println("Login:");
        String name = scanner.next();
        System.out.println("Permissions:");
        String perm = scanner.next();
        userManager.add(new User(name, perm, 1));
    }

    private void updateUser() throws ExceptionDAO {
        System.out.println("User login:");
        String login = scanner.next();
        User user = userManager.get(login);
        System.out.println("Login:(0 for origin)");
        String temp = scanner.next();
        String newLogin;
        if (temp.equals("0")) newLogin = login;
        else newLogin = temp;
        System.out.println("Permissions:(0 for origin)");
        temp = scanner.next();
        String newPermissions;
        if (temp.equals("0")) newPermissions = user.getPermission();
        else newPermissions = temp;
        System.out.println("Id of current planet:(0 for origin)");
        int tempInt = scanner.nextInt();
        int newIdPlanet;
        if (tempInt == 0) newIdPlanet = user.getIdCurrentPlanet();
        else newIdPlanet = tempInt;
        userManager.update(user.getId(), new User(user.getId(), newLogin, newPermissions, newIdPlanet));
    }

    private void deleteUser() throws ExceptionDAO {
        System.out.println("User login:");
        String login = scanner.next();
        userManager.delete(userManager.get(login).getId());
    }

    private void changeUserMenu() throws ExceptionDAO {
        while (true) {
            System.out.print("1.Add user\n" +
                    "2.Change data\n" +
                    "3.Delete user\n" +
                    "4.Back\n");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                default:
                    return;
            }
        }
    }

    private void adminMenu() throws ExceptionDAO {
        while (true) {
            System.out.print("1.Add planet\n" +
                    "2.Change existing planet\n" +
                    "3.Delete planet\n" +
                    "4.Change user info\n" +
                    "5.Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPlanet();
                    break;
                case 2:
                    updatePlanet();
                    break;
                case 3:
                    deletePlanet();
                    break;
                case 4:
                    changeUserMenu();
                    break;
                case 5:
                    break;
                default:
                    return;
            }
        }
    }

    public void mainMenu() throws ExceptionDAO {
        changeUser();
        int choice;
        while (true) {
            System.out.print("What you want to do?\n" +
                    "1.Move to another planet\n" +
                    "2.Planet info\n" +
                    "3.Available planets\n" +
                    "4.Admin menu\n" +
                    "5.Change user\n" +
                    "6.Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    moveUser();
                    break;
                case 2:
                    planetUIList.get(findT(planetUIList, user.getIdCurrentPlanet())).view();
                    break;
                case 3:
                    viewPlanets();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 5:
                    changeUser();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }
}