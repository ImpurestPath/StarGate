package ru.ifmo.oop.dataAccess.exception;

public class ExceptionDAO extends Exception {
    public ExceptionDAO(Throwable e) {
        initCause(e);
    }
}
