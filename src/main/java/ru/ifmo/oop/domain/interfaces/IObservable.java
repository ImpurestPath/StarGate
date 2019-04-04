package ru.ifmo.oop.domain.interfaces;

public interface IObservable<T> {
    void addListener(Listener listener);
    void deleteListener(Listener listener);
    void notifyListeners(double value);
    void finishListeners();
    void call() throws Exception;
    T getResult();
    T mainActivity() throws Exception;
}
