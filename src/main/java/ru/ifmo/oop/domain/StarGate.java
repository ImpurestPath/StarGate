package ru.ifmo.oop.domain;

public class StarGate {
    //TODO make interface and manager
    public static void moveUser(int id, User user) {
        user.idCurrentPlanet = id;
    }
}
