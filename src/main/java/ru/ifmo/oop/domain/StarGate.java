package ru.ifmo.oop.domain;

public class StarGate {
    public static void moveUser(int id, User user) {
        user.idCurrentPlanet = id;
    }
}
