package ru.ifmo.oop.domain;

public interface Listener {
    void handle(double progress);
    void onFinish();
}
