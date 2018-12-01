package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

public interface DAO<T> {
    int add(T obj) throws ExceptionDAO;
    void update(T obj) throws ExceptionDAO;
    void delete(int id) throws ExceptionDAO;
    T get(int id) throws ExceptionDAO;
}
