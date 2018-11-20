//package ru.ifmo.oop;
//
//import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;
//import ru.ifmo.oop.domain.PlanetManager;
//import ru.ifmo.oop.domain.UIUserManager;
//import ru.ifmo.oop.ui.Console;
//
//
//public class Main {
//
//    public static void main(String[] args) throws ExceptionDAO {
//        PlanetManager manager = new PlanetManager("PlanetRepository.db");
//        UIUserManager userManager = new UIUserManager("PlanetRepository.db");
//        Console console = new Console(manager,userManager,1);
//        Thread loadPlanets = new Thread(console.new LoadPlanets());
//        Thread loadingScreen = new Thread(console.new LoadingScreen());
//        loadPlanets.start();
//        loadingScreen.start();
//        try {
//            loadPlanets.join();
//        } catch (InterruptedException e){
//            return;
//        }
//        loadingScreen.interrupt();
//        console.mainMenu();
//    }
//}
