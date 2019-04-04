package ru.ifmo.oop.domain.interfaces;

public interface WithResult<T> {
    T getResult() throws Exception;
}
