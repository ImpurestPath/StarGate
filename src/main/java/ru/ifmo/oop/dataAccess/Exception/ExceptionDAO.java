package ru.ifmo.oop.dataAccess.Exception;

public class ExceptionDAO extends Exception {
    public ExceptionDAO(Throwable e) {
        initCause(e);
    }
}
