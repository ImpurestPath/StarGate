package ru.ifmo.oop.db.Exception;

public class ExceptionDAO extends Exception {
    public ExceptionDAO(Throwable e) {
        initCause(e);
    }
}
