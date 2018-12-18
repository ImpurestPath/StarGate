package ru.ifmo.oop.domain;

import java.util.List;

public interface IPlanetManager extends Manager<Planet> {
    Observable<List<Planet>> getAll();
}
