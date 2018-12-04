package ru.ifmo.oop.dataAccess.exception;

public class ConnectionError extends Exception {
    public ConnectionError(Throwable e){
        initCause(e);
    }
}
