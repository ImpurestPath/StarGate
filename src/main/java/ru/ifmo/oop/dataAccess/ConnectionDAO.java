package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;

public interface ConnectionDAO<T> {
    void commit() throws DatabaseError;
    T getConnection();
}
