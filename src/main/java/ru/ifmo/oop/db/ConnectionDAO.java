package ru.ifmo.oop.db;

import ru.ifmo.oop.db.Exception.ExceptionDAO;

public interface ConnectionDAO {
    void commit() throws ExceptionDAO;

}
