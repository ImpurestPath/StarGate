package ru.ifmo.oop.domain;

public class StarGateController implements IStarGate {
    /*public static void moveUser(int id, User user) {
        user.idCurrentPlanet = id;
    }*/
    public StarGateController() {
    }

    @Override
    public void moveUser(int id, User user) {
        user.idCurrentPlanet = id;
    }
}
