package ru.ifmo.oop.domain;

import ru.ifmo.oop.domain.interfaces.IObservable;
import ru.ifmo.oop.domain.interfaces.Listener;
import ru.ifmo.oop.domain.interfaces.WithResult;

import java.util.ArrayList;
import java.util.List;

abstract public class Observable<T> implements IObservable<T> {
    private WithResult<T> func;
    private T result;
    private List<Listener> listeners;
    private Thread running;

    public Observable(WithResult<T> func) {
        this.func = func;
        this.result = null;
        this.listeners = new ArrayList<>();
    }

    public Observable() {
        this.result = null;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void deleteListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(double value) {
        for (Listener listener : listeners){
            listener.handle(value);
        }
    }
    public void updateProgress(double progress){
        notifyListeners(progress);
    }
    public void setFinished(){
        finishListeners();
    }
    @Override
    public void finishListeners() {
        for (Listener listener : listeners){
            listener.onFinish();
        }
    }
    @Override
    public void call() throws Exception {
        running = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = mainActivity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        running.start();
    }

    @Override
    public T getResult()  {
        if (running != null) {
            try {
                running.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

}
