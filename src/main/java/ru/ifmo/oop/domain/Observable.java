package ru.ifmo.oop.domain;

public interface Observable<T> {
    void addListener(Listener listener);
    void deleteListener(Listener listener);
    void notifyListeners(double value);
    void finishListeners();
    T call() throws Exception;
}
