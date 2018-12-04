package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;

public interface DAO<T> {
    int add(T obj) throws DatabaseError;
    void update(T obj) throws DatabaseError;
    void delete(int id) throws DatabaseError;
    T get(int id) throws DatabaseError;
}
