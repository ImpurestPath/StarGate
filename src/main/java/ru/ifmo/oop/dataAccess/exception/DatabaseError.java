package ru.ifmo.oop.dataAccess.exception;

public class DatabaseError extends Exception {
    public DatabaseError(Throwable e){
        initCause(e);
    }
}
