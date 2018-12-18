package ru.ifmo.oop.domain;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;

public interface Manager<T> {
    T get(int id) throws DatabaseError;

    void add(T obj) throws DatabaseError;

    void delete(T obj) throws DatabaseError;

    void update(T obj) throws DatabaseError;
}
