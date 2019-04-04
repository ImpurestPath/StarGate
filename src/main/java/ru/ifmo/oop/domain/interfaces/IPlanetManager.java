package ru.ifmo.oop.domain.interfaces;

import ru.ifmo.oop.domain.Observable;
import ru.ifmo.oop.domain.Planet;

import java.util.List;

public interface IPlanetManager extends Manager<Planet> {
    Observable<List<Planet>> getAll();
}
