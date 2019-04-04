package ru.ifmo.oop.domain.interfaces;

public interface Listener {
    void handle(double progress);
    void onFinish();
}
